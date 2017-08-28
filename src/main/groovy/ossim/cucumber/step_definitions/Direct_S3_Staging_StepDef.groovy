package ossim.cucumber.step_definitions

import ossim.cucumber.config.CucumberConfig

import ossim.cucumber.ogc.WFSCall

import java.nio.charset.Charset

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

String defaultCharset = Charset.defaultCharset().displayName()

config = CucumberConfig.config
def stagingService = config.stagingService

static String getFilename(platform, extension, remoteType ) {
    platform = platform.toLowerCase()
    extension = extension.toLowerCase()
    remoteType = remoteType.toLowerCase()


    return config.images.remote."${platform}"."${extension}"."${remoteType}"
}

Given(~/^an AWS Remote (.*) (.*) (.*) image is not already indexed$/) {
    String index, String platform, String remoteType ->

    def filename = getFilename( index, platform, remoteType )
    println "Searching for ${filename}"

    def filter = "filename = '${filename}'"
    def wfsQuery = new WFSCall(config.wfsServerProperty, filter, "JSON", 1)
    def features = wfsQuery.result.features

    // if any files are found, delete them
    if (features.size() > 0) {
        println "... It's already indexed!"
        features.each() {
            String file = it.properties.filename
            println "Removing ${file} from database"
            def removeRasterUrl = "${stagingService}/removeRaster?filename=${URLEncoder.encode(file, defaultCharset)}"
            def command = "curl -X POST ${removeRasterUrl}"
            def process = command.execute()
            process.waitFor()
            println "... Remnoved!"
        }

        // redo the WFS query to see if the files have been removed
        println "Checking to make sure they are removed..."
        wfsQuery = new WFSCall(config.wfsServerProperty, filter, "JSON", 1)

        features = wfsQuery.result.features
        if (features.size() == 0) { println "Image has been removed" }
        else { println "... doesn't look like it was removed." }
    }
    else { println "... Not indexed yet." }


    assert features.size() == 0
}

When( ~/^an AWS Remote (.*) (.*) (.*) image is indexed into OMAR$/ ) {
    String index, String platform, String remoteType ->

    def filename = getFilename( index, platform, remoteType )

    def addRasterUrl = "${stagingService}/addRaster?buildOverviews=false&buildHistograms=false&background=false&filename=${URLEncoder.encode(filename, defaultCharset)}"
    def command = "curl -X POST ${addRasterUrl}"
    def process = command.execute()
    process.waitFor()

    println "addRaster Result: ${process.text}"
}

Then(~/^an AWS Remote (.*) (.*) (.*) image should be available$/) {
    String index, String platform, String remoteType ->
    def filename = getFilename( index, platform, remoteType )
    def features

    println new Date()
    println "Has ${filename} been ingested?..."

    def filter = "filename = '${filename}'"
    def wfsQuery = new WFSCall(config.wfsServerProperty, filter, "JSON", 1)
    features = wfsQuery.result.features
    if (features.size() > 0) {
        println "... Yes!!!"
    }

    assert features.size() == 1
}

