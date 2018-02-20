package ossim.cucumber.step_definitions

import ossim.cucumber.config.CucumberConfig

import ossim.cucumber.ogc.wfs.WFSCall

import java.nio.charset.Charset

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

String defaultCharset = Charset.defaultCharset().displayName()

config = CucumberConfig.config
def stagingService = config.stagingService

String getFilename(location, subCategory , type ) {
    location = location.toLowerCase()
    subCategory = subCategory.toLowerCase()
    type = type.toLowerCase()


    return config.images."${location}"."${subCategory}"."${type}"
}

Given(~/^a (.*) (.*) (.*) image is not already indexed$/) {
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
            def command = ["curl",
                                    "-X",
                                    "POST",
                                    "${removeRasterUrl}"
                                ]
            /*
                add an ArrayList called curlOptions to the config file if
                addition info needs to be added to the curl command.
            */
            if (config?.curlOptions)
            {
                command.addAll(1, config.curlOptions)
            }
            println command
            def process = command.execute()
            process.waitFor()
            println "... Removed!"
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

When( ~/^a (.*) (.*) (.*) image is indexed into OMAR$/ ) {
    String location, String subCategory, String type ->

    def filename = getFilename( location, subCategory, type )
println "Trying to index filename ${filename}"
    def addRasterUrl = "${stagingService}/addRaster?buildOverviews=true&buildHistograms=true&background=false&filename=${URLEncoder.encode(filename, defaultCharset)}"
    def command = ["curl",
                            "-X",
                            "POST",
                            "${addRasterUrl}"
                        ]
    /*
        add an ArrayList called curlOptions to the config file if
        addition info needs to be added to the curl command.
    */
    if (config?.curlOptions)
    {
        command.addAll(1, config.curlOptions)
    }
    println command
    println "CALLING POST ON URL: ${command}"
    def process = command.execute()
    process.waitFor()

    println "addRaster Result: ${process.text}"
}

Then(~/^a (.*) (.*) (.*) image should be available$/) {
    String location, String subCategory, String type ->
    def filename = getFilename( location, subCategory, type )
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
