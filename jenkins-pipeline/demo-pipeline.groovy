
    node () {

        def isBuildFailure = false
        //def gridURL = "${env.GRIDLASTIC_URL}"
        def application = 'bsd'
        def browser = 'chrome'
        def testName = 'CatalogTest_BSD'
        def environment = 'PROD'
        def repoName = 'prod'
        def testUrl = 'https://business.officedepot.com'
        def testDataFile = 'TestData-Prod.xml'
        def testngFile= 'testng-specialprojects.xml'
        def repo = 'https://github.com/officedepot/bsd-automation-reloaded.git'
        def PARAMS
        def emailList = 'qa-automation-team@officedepot.com'
        def constants
        def gitId
        def svnId
        def testngFilesList
        def testdataFilesList
        def wwwTestUrl
        def packageName = 'SpecialProjects'
        def mavenContainerVersion
        def dataProviderFileList
        def testngFilePath = 'resources/testng/'
        def testngFailedXml

        //ReportPortal Props
        def rpEndpoint
        def rpUuid
        def rpProject
        def rpLaunch
        def rpTags
        def rpDescription
        stage("Read Constants and Shared Library") {
            script {
                constants = evaluate readTrusted('jenkins_pipeline/pipeline_constants.groovy')
                sharedLib = evaluate readTrusted('jenkins_pipeline/common_lib.groovy')
                gitId = "${constants.gitId}"
                testngFilesList = "${constants.testngFilesList}"
                testdataFilesList = "${constants.testdataFilesList}"
                svnId = "${constants.svnId}"
                wwwTestUrl = "${constants.wwwTestUrl}"
                mavenContainerVersion = "${constants.mavenContainerVersion}"
            }
        }

        stage("Get Package Names from SCM") {
            container('ctmgit') {
                checkout([
                        $class                           : 'GitSCM',
                        branches                         : [[name: '*/master']],
                        doGenerateSubmoduleConfigurations: false,
                        extensions                       : [],
                        submoduleCfg                     : [],
                        userRemoteConfigs                : [[credentialsId: "${gitId}", url: 'https://github.com/rakeshsun/devops-project.git']]])
            }
}
}
