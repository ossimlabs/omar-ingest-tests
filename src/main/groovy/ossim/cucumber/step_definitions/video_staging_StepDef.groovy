package ossim.cucumber.step_definitions

import ossim.cucumber.config.CucumberConfig

import ossim.cucumber.ogc.wfs.WFSCall

import java.nio.charset.Charset

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

String defaultCharset = Charset.defaultCharset().displayName()

config = CucumberConfig.config
def stagingService = config.stagingService
def filename = "/data/videos/uav/predator/MISP-_42FB6D65_21FEB03000019071saMISP-_HD000999.mpg"

Given(~/^the video (.*) is not already staged$/) {
    
    def filter = "filename = '${filename}'"
    def wfsQuery = new WFSCall(config.wfsServerProperty, filter, "JSON", 1, "omar:video_data_set")
    def features = wfsQuery.result.features

    // if any files are found, delete them
    if (features.size() > 0) {
        println "... It's already indexed!"
        features.each() {
            String file = it.properties.filename
            println "Removing ${file} from database"
            def removeVideoUrl = "${stagingService}/removeVideo?filename=${URLEncoder.encode(file, defaultCharset)}"
            def command = ["curl",
                                    "-u",
                                    "${config.curlUname}",
                                    "-X",
                                    "POST",
                                    "${removeVideoUrl}"
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
        wfsQuery = new WFSCall(config.wfsServerProperty, filter, "JSON", 1, "omar:video_data_set")

        features = wfsQuery.result.features
        if (features.size() == 0) { println "Video has been removed" }
        else { println "... doesn't look like it was removed." }
    }
    else { println "... Not indexed yet." }


    assert features.size() == 0
}

When( ~/^an AWS Remote (.*) video is indexed into OMAR$/ ) {

    def addVideoUrl = "${stagingService}/addVideo?filename=${URLEncoder.encode(filename, defaultCharset)}"
    def command = ["curl",
                            "-X",
                            "POST",
                            "${addVideoUrl}"
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

    println "addVideo Result: ${process.text}"
}

Then(~/^the video (.*) should be discoverable$/) {

    println new Date()
    println "Has ${filename} been ingested?..."

    def filter = "filename = '${filename}'"
    def wfsQuery = new WFSCall(config.wfsServerProperty, filter, "JSON", 1, "omar:video_data_set")
    features = wfsQuery.result.features
    if (features.size() > 0) {
        println "... Yes!!!"
    }

    assert features.size() == 1
}
