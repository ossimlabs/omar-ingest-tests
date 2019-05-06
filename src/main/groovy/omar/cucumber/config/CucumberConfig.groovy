package omar.cucumber.config

import groovy.text.*
import groovy.json.JsonSlurper

class CucumberConfig
{
    static def config

    static def getConfig()
    {

        if (!config)
        {
            init()
        }

        config
    }

    private static void init()
    {
        def resourceFile

        if (System.env.CUCUMBER_CONFIG_LOCATION)
        {
            File testForLocal = new File(System.env.CUCUMBER_CONFIG_LOCATION);
            if (!testForLocal.exists())
            {
                resourceFile = new URL(System.env.CUCUMBER_CONFIG_LOCATION)
            }
            else
            {
                resourceFile = testForLocal.toURL()
            }
        }
        else
        {
            resourceFile = new File("cucumber-config.groovy");
            if (resourceFile.exists())
            {
                resourceFile = resourceFile.toURL()
            }
            else
            {
                resourceFile = null
            }
            if (!resourceFile)
            {
                resourceFile = new File("src/main/resources/cucumber-config.groovy").toURI().toURL()
            }
        }

        if (resourceFile)
        {
            def slurper = new ConfigSlurper()
            config = slurper.parse(resourceFile)
        }

        createFeatureFile()
    }
    
    static void createFeatureFile() {
        def config =  getConfig()
        config.featureTemplateFiles.templates.each{ files ->
            List list = []
            File templateFile = new File("${files.getValue().template_file}")
            File featureFile =  new File("${files.getValue().feature_file}")
            
            config.image_files.each{ imagesList -> 
                if(files.getValue().data == imagesList.getKey().toString())
                {
                    imagesList.getValue().images.each{ imageData ->
                        imageData.each { imageInfo ->
                            list.push(imageInfo.getValue().image_id)
                        }
                    }
                
                }             
            }

            HashMap imageList = ["files":list]
            SimpleTemplateEngine engine = new SimpleTemplateEngine()
            Template template = engine.createTemplate(templateFile)
            Writable writable = template.make(imageList)
            featureFile.write(writable.toString())
        }
    }
}
