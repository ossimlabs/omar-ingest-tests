package omar.cucumber.ingest.test

import omar.cucumber.ingest.test.TomcatStart

class CucumberTest {
    public void startTest() {
        String main_path = 'src/main'
        String resource_path = 'src/main/resources'
        String ingestTags = "@local_staging_service, @staging_service, @direct_s3_staging_service"
        String jsonFile = "ingest.json"

        if (System.getProperty("mainPath"))
        {
            main_path = System.getProperty("mainPath")
        }

        if (System.getProperty("resourcePath"))
        {
            resource_path = System.getProperty("resourcePath")
        }

        if (System.getenv("multi") == "true")
        {
            ingestTags = "@staging_multi_ingest_service"
            jsonFile = "ingestMultipleImages.json"
        }

        String[] arguments = [
                "--tags",
                "${ingestTags}",
                "--tags",
                "~@C2S",
                '--plugin', "json:src/main/groovy/omar/webapp/${jsonFile}",
                '--plugin', "html:src/main/groovy/omar/webapp/html",
                '--plugin', "pretty",
                '--glue', main_path,
                resource_path]
        cucumber.api.cli.Main.main(arguments)
    }
}