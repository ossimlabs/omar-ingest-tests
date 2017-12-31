# omar-ingest-tests

### Purpose

The purpose of the omar-ingest-tests project is to provide a suite of automated tests for different OMAR ingest services. These tests run nightly and when certain OMAR projects are built. The current status of the tests helps developers to ensure that OMAR's ingest services are running correctly.

### Content

The omar-ingest-tests are composed of 3 sets of tests, each for a different service.

These tests are made using Cucumber, a test language that resembles basic English. This allows anyone to look at the tests and understand their purpose and intent. Using the Cucumber tests as a guide, the tests are then converted into code. In the case of the backend tests, Groovy is used to programatically test the capabilities of the backend services.

- *Staging* - tests the OMAR Stager. Downloads images from AWS by sending an AVRO mesage to omar-avro, creates overviews and histograms for them using omar-stager, and then indexes them using omar-stager.
- *DirectS3Staging* - tests the OMAR DirectS3 Staging. Indexes images on S3 directly using the omar-stager.

More details on the content of the tests can be found in the Cucumber *.feature* files located in *src/cucumber/resources/features*

### Automated Execution

The ingest tests are automatically executed using a Jenkins build on https://jenkins.ossim.io - the job name is omar-ingest-tests-dev for the dev branch and omar-ingest-tests-release for the master branch. The tests run weekly.

### Manual Execution

Running the ingest tests manually requires Gradle. With Gradle installed, run the *gradle ingest* command from the project's base directory in order to execute the *ingest* task defined in the project's Gradle Build file.

The default config file uses the dev deployment of OMAR located at https://omar-dev.ossim.io
