throttle(['external_grid']) {
	node ('ctm-bsd') {
		def isBuildFailure = false
		def gridURL = 'http://SAx7nLkcZ0JCWZj6VHa8RTKX9Umh1SIK:i6HwHSzxJ8ZvY8sJFtInciab3niV5Rve@OD.gridlastic.com:80/wd/hub'
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
		def emailList = 'ecommateam@officedepot.com'
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
				$class: 'GitSCM',
				branches: [[name: '*/master']],
				doGenerateSubmoduleConfigurations: false,
				extensions: [],
				submoduleCfg: [],
				userRemoteConfigs: [[credentialsId: "${gitId}", url: 'https://github.com/officedepot/bsd-automation-reloaded.git']]])
			}
			script {
				GIT_PROJECT_PACKAGES = sh (
					script: "git ls-tree --name-only -r HEAD:src/com/officedepot/SpecialProjects/",
					returnStdout: true
					).trim()
					def packageNamesList = GIT_PROJECT_PACKAGES.split("[\\r\\n]+").collect{it}
					packageNamesList.removeAll{ it.contains('TCRequireModification') }
					packageNamesList.removeAll(['duplicate', 'BlockedTestCases', 'bsd','surefire', 'unReviewed', 'SpecialProjects' ] as Object[])
					env.PACKAGES = packageNamesList.join("\n")
				}
			}
			
				
				stage ('Evaluate') {
					script {
						echo "In evaluate stage"
						
						if (testngFile == 'ctm-failed.xml') {
							testngFailedXml = "ctm-failed-${application}-${PARAMS['packageName']}.xml"
							testngFilePath = "/root/CTM_Automation/CTM_Reports/${testngFailedXml}"
						}
						else {
							testngFilePath = "resources/testng/${testngFile}"
						}
					}
				}
				stage ("Build Parameter Info - ${application} - ${testName} - ${environment} - ${browser} - ${repoName} - ${testUrl}") {
					echo "${application} - ${testName} - ${environment} - ${browser} - ${repoName} - ${testUrl} - ${testDataFile}  - ${testngFilePath}"
				}
				stage ("${application} - ${packageName} - ${environment}  - Checkout") {
				container('ctmgit') {
					
				         if (repoName == 'prod') {
				          echo 'Checking out ' + "${application}" + ' prod'
				          checkout([$class: 'GitSCM', branches: [
				          [name: '*/prod']
				          ], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [
				          [credentialsId: "${gitId}", url: "${repo}"]
				          ]])
				        }
					}
			}
				stage ("${application} - ${testName} - ${environment} - Build") {
					container("${mavenContainerVersion}"){
						script {
							try {
								configFileProvider([configFile(fileId: 'mvn_settings', targetLocation: 'settings.xml')]) {
									sh """mvn --settings settings.xml -B -q test -DpackageName="${packageName}" -DtestName="${testName}" -DbrowserName="${browser}" -DenableGrid=true -Denvironment="${environment}" -DtestUrl="${testUrl}" -DtestDataFile="${testDataFile}" -DtestngFile=${testngFilePath} -DgridURL=${gridURL} -Dapplication="${application}" """
									//cleanWs()
									
								}
							}
							catch (err) {
								//cleanWs()
								if(manager.logContains(".*CTM Test Suite Execution Complete.*")) {
									if(manager.logContains(".*BUILD FAILURE.*")) {
										isBuildFailure = true
										currentBuild.result = 'SUCCESS'
									}
								}
								else {
									isBuildFailure = true
									currentBuild.result = 'FAILURE'
									sh "exit 1"
								}
							}
						}
					}
				}
				stage('Email User') {
					container("${mavenContainerVersion}"){
						script {
							buildUrl = "${BUILD_URL}"
							if(testName == 'CatalogTest_BSD') {
								if (!manager.logContains(".*Failures.*")) {
									sharedLib.emailUsers(isBuildFailure, "${application}", "${testName}", "${environment}", "${browser}", "${testUrl}", buildUrl, "${constants.emailDistributionList.catalogSuccess}", "BSD Catalog Passed", "Passed",  "**/catalog-bsd.xls")
								}
								else {
									sharedLib.emailUsers(isBuildFailure, "${application}", "${testName}", "${environment}", "${browser}", "${testUrl}", buildUrl, "${constants.emailDistributionList.catalogFailure}", "BSD Catalog Failed", "Failed", "**/catalog-bsd.xls")
								}
							}
						}
						cleanWs()
					}
				}
				stage ('Upload_Results_to_Database - Checkout') {
					container("${mavenContainerVersion}"){
						checkout([$class: 'GitSCM', branches: [[name: '*/nonprod']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: "${gitId}", url: 'https://github.com/officedepot/ctm-results-loader.git']]])
					}
				}
				stage ('Upload_Results_to_Database - Build') {
				container("${mavenContainerVersion}"){
					configFileProvider([configFile(fileId: 'mvn_settings', targetLocation: 'settings.xml')]) {
						sh """mvn --settings settings.xml --batch-mode install exec:java -Dexec.mainClass="com.cleartestmax.resultsloader.ResultsToDB" -Dexec.cleanupDaemonThreads=false"""
					}
				}
			}
				//}
				//}
			}
		}
