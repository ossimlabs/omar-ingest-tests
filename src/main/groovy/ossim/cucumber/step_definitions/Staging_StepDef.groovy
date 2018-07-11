package ossim.cucumber.step_definitions

import com.amazonaws.services.sqs.AmazonSQSClient
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import ossim.cucumber.config.CucumberConfig

import ossim.cucumber.ogc.wfs.WFSCall

import java.nio.charset.Charset

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

String defaultCharset = Charset.defaultCharset().displayName()

config = CucumberConfig.config
def bucketUrl = config.s3BucketUrl
def imageBucket = config.s3Bucket
def stagingService = config.stagingService
def wfsServer = config.wfsServerProperty
def waitForStage = config.waitForStage ?: 10
def sqsTimestampName = config.sqsTimestampName

def getImageId(format, index, platform, sensor)
{
    format = format.toLowerCase()
    platform = platform.toLowerCase()
    sensor = sensor.toLowerCase()


    return config.images[platform][sensor][format][index == "another" ? 1 : 0]
}

Given(~/^(.*) (.*) (.*) (.*) is not already staged$/) {
    String index, String platform, String sensor, String format ->

        def imageId = getImageId(format, index, platform, sensor)
        println "Searching for ${imageId}"

        def filter = "filename LIKE '%${imageId}%'"
        def wfsQuery = new WFSCall(wfsServer, filter, "JSON", 1)
        def features = wfsQuery.result.features

        // if any files are found, delete them
        if (features.size() > 0)
        {
            println "... It's already staged!"
            features.each() {
                def filename = it.properties.filename
                println "Deleting ${filename}"
                def removeRasterUrl = "${stagingService}/removeRaster?deleteFiles=true&filename=${URLEncoder.encode(filename, defaultCharset)}"
                def command = ["curl",
                                        "-u",
                                        "${config.curlUname}",
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
                println "... Deleted!"
            }

            // redo the WFS query to see if the files have been removed
            println "Checking to make sure they are deleted..."
            wfsQuery = new WFSCall(wfsServer, filter, "JSON", 1)

            features = wfsQuery.result.features
            if (features.size() == 0)
            {
                println "... Yup, it's gone!"
            }
            else
            {
                println "... Uh oh, doesn't look like it was deleted."
            }
        }
        else
        {
            println "... Not staged yet."
        }


        assert features.size() == 0
}

When(~/^(.*) (.*) (.*) (.*) AVRO message is placed on the SQS$/) {
    String index, String platform, String sensor, String format ->

        def imageId = getImageId(format, index, platform, sensor)
        println "Sending ${imageId}'s AVRO message to the SQS"

        def command = ["curl",
                                "-kLs",
                                "${bucketUrl}/${imageBucket}/json_files/${imageId}.json"
                            ]

        println command
        def process = command.execute()
        process.waitFor()

        def text = process.getText()
        def json = new JsonSlurper().parseText(text)
        json."${sqsTimestampName}" = new Date().format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", TimeZone.getTimeZone("UTC"))
        def newSqsText = new JsonBuilder(json).toString()

        def sqs = AmazonSQSClient.newInstance()
        sqs.sendMessage(config.sqsStagingQueue, newSqsText)

        println "... Sent!"
}

Then(~/^(.*) (.*) (.*) (.*) should be discoverable$/) {
    String index, String platform, String sensor, String format ->

        def imageId = getImageId(format, index, platform, sensor)
        def features

        println new Date()
        println "Has ${imageId} been ingested yet?..."

        def timer = 60 * waitForStage
        while (timer > 0)
        {
            sleep(5000)
            def filter = "filename LIKE '%${imageId}%'"
            def wfsQuery = new WFSCall(wfsServer, filter, "JSON", 1)
            features = wfsQuery.result.features
            if (features.size() > 0)
            {
                println "... Yes!!!"
                timer = 0
            }
            else
            {
                print "... "
                timer -= 5
            }
        }


        assert features.size() == 1
}
