package ossim.cucumber.step_definitions

import ossim.cucumber.config.CucumberConfig

import ossim.cucumber.ogc.wfs.WFSCall

import java.nio.charset.Charset

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

String defaultCharset = Charset.defaultCharset().displayName()

config = CucumberConfig.config
def stagingService = config.stagingService

//////////////////////////////////////////////////////////////////////////////////////////////

Given(~/^the video (.*) is not already staged$/) { String video ->

  println "==========="
  println "Searching for video ${video}"

  def filter = "filename LIKE '%${video}%'"
  def wfsQuery = new WFSCall(wfsServer, filter, "JSON", 1)
  def features = wfsQuery.result.features

  // if any files are found, delete them
  if (features.size() > 0)
  {
      println "... It's already staged!"
      features.each() {
          def filename = it.properties.filename
          println "Deleting ${filename}"
          def removeVideoUrl = "${stagingService}/removeVideo?deleteFiles=true&filename=${URLEncoder.encode(filename, defaultCharset)}"
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

When( ~/^an AWS Remote (.*) video is indexed into OMAR$/ ) {

    def filename = "MISP-_42FB6DA1_21FEB03000019081saMISP-_HD000999.mpg"

    def addVideoUrl = "${stagingService}/addVideo?buildOverviews=false&buildHistograms=false&background=false&filename=${URLEncoder.encode(filename, defaultCharset)}"
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

    println "addRaster Result: ${process.text}"
}

Then(~/^the video (.*) should be discoverable$/) { String video ->
  def features

  println new Date()
  println "Has ${video} been ingested yet?..."

  def timer = 60 * waitForStage
  while (timer > 0)
  {
      sleep(5000)
      def filter = "filename LIKE '%${video}%'"
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

  // Save the file path for later
  filepath = features[0]["properties"]["filename"]
}
