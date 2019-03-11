properties([
    parameters ([
        string(name: 'BUILD_NODE', defaultValue: 'ossim-test-build', description: 'The build node to run on'),
        string(name: 'TARGET_DEPLOYMENT', defaultValue: 'dev', description: 'The deployment to run the tests against'),
        booleanParam(name: 'MULTI_INGEST', defaultValue: false, description: 'Run a multi-ingest test'),
        booleanParam(name: 'CLEAN_WORKSPACE', defaultValue: true, description: 'Clean the workspace at the end of the run'),
    ]),
    pipelineTriggers([
            [$class: "GitHubPushTrigger"]
    ]),
    [$class: 'GithubProjectProperty', displayName: '', projectUrlStr: 'https://github.com/ossimlabs/omar-ingest-tests'],
    disableConcurrentBuilds()
])

String gradleTask
String outputJson

if ("${MULTI_INGEST}" == "true") {
    gradleTask = "ingestMultipleImages"
    outputJson = "ingestMultipleImages.json"
} else {
    gradleTask = "ingest"
    outputJson = "ingest.json"
}

node("${BUILD_NODE}"){

    stage("Checkout branch $BRANCH_NAME")
    {
        checkout(scm)
    }

    stage("Load Variables")
    {
        withCredentials([string(credentialsId: 'o2-artifact-project', variable: 'o2ArtifactProject')]) {
            step ([$class: "CopyArtifact",
                projectName: o2ArtifactProject,
                filter: "common-variables.groovy",
                flatten: true])
        }

        load "common-variables.groovy"
    }

    stage("Pull Artifacts")
    {
        step ([$class: "CopyArtifact",
            projectName: "ossim-ci",
            filter: "cucumber-configs/cucumber-config-ingest.groovy",
            flatten: true])
    }    

    try {
        stage ("Run Test")
        {
            withCredentials([[$class: 'UsernamePasswordMultiBinding',
                        credentialsId: 'curlCredentials',
                        usernameVariable: 'CURL_USER_NAME',
                        passwordVariable: 'CURL_PASSWORD']])
            {
                sh """
                    echo "TARGET_DEPLOYMENT = ${TARGET_DEPLOYMENT}"
                    export CUCUMBER_CONFIG_LOCATION="cucumber-config-ingest.groovy"
                    export DISPLAY=":1"
                    gradle ${gradleTask}
                """
            }
        }
    } finally {
        stage("Archive"){
            sh "cp build/${outputJson} ."
            archiveArtifacts "${outputJson}"
        }

        stage("Publish Report") {
            step([$class: 'CucumberReportPublisher',
                fileExcludePattern: '',
                fileIncludePattern: '',
                ignoreFailedTests: false,
                jenkinsBasePath: '',
                jsonReportDirectory: "build",
                parallelTesting: false,
                pendingFails: false,
                skippedFails: false,
                undefinedFails: false])
            cucumberSlackSend channel: '#ossimlabs_jenkins', json: "build/${outputJson}"
        }
    }
        
    stage("Clean Workspace")
    {
        if ("${CLEAN_WORKSPACE}" == "true")
            step([$class: 'WsCleanup'])
    }
}
