def emailUsers(isBuildFailure, projectName, branchName, buildUrl, emailList) {
  if (!isBuildFailure) {
    emailext body: "${env.JOB_BASE_NAME}" + " -- " + "${projectName}" + ' -- ' + "${branchName}" + ' ---> Passed' + "\n" + "${BUILD_URL}", subject: "${env.JOB_BASE_NAME}" + " -- " + "${projectName}" + ' -- ' + "${branchName}"+ ' ---> Passed', to: "${emailList}"
    } else {
      emailext body: "${env.JOB_BASE_NAME}" + " -- " + "${projectName}" + ' -- ' + "${branchName}" + ' ---> Failed' + "\n" + "${BUILD_URL}", subject: "${env.JOB_BASE_NAME}" + " -- " + "${projectName}" + ' -- ' + "${branchName}" + ' ---> Failed', to: "${emailList}"
    }
}
def emailUsers(emailList) {
      emailext body: "${env.JOB_BASE_NAME}" + " -- "  + ' ---> Aborted '+'\nBuild Url: ' + "${BUILD_URL}", subject: "${env.JOB_BASE_NAME}"  + ' ---> Aborted', to: "${emailList}"
}

def emailUsers(isBuildFailure, application, packageName, environment, browser, testUrl, buildUrl, emailList) {
  if (!isBuildFailure) {
    emailext body: "${env.JOB_BASE_NAME}" + '\nApplication: '+ "${application}" + '\nModule: ' + "${packageName}" + '\nEnvironment: ' + "${environment}" + '\nBrowser: ' + "${browser}" + '\nTestUrl: ' + "${testUrl}" + '\nBuild Url: ' + "${BUILD_URL}" + '\nStatus: Passed', subject: "${application}" + ' -- ' + "${packageName}" + ' -- ' + "${environment}"+ ' -- Status: Passed' , to: "${emailList}"
    } else {
      emailext body: "${env.JOB_BASE_NAME}" + '\nApplication: '+ "${application}" + '\nModule: ' + "${packageName}" + '\nEnvironment: ' + "${environment}" + '\nBrowser: ' + "${browser}" + '\nTestUrl: ' + "${testUrl}" + '\nBuild Url: ' + "${BUILD_URL}" + '\nStatus: Failed', subject: "${application}" + ' -- ' + "${packageName}" + ' -- ' + "${environment}"+ ' -- Status: Passed', to: "${emailList}"
    }
}


def emailUsers(isBuildFailure, application, packageName, environment, browser, platformType, platformVersion, testUrl, buildUrl, emailList) {
  if (!isBuildFailure) {
    emailext body: "${env.JOB_BASE_NAME}" + '\nApplication: '+ "${application}" + '\nModule: ' + "${packageName}" + '\nEnvironment: ' + "${environment}" + '\nBrowser: ' + "${browser}" + '\nPlatform Type: ' + "${platformType}" + '\nPlatform Version' + "${platformVersion}" + '\nTestUrl: ' + "${testUrl}" + '\nBuild Url: ' + "${BUILD_URL}" + '\nStatus: Passed', subject: "${application}" + ' -- ' + "${packageName}" + ' -- ' + "${environment}"+ ' -- Status: Passed' , to: "${emailList}"
    } else {
      emailext body: "${env.JOB_BASE_NAME}" + '\nApplication: '+ "${application}" + '\nModule: ' + "${packageName}" + '\nEnvironment: ' + "${environment}" + '\nBrowser: ' + "${browser}" + "${platformType}" + '\nPlatform Version' + "${platformVersion}" + '\nTestUrl: ' + "${testUrl}" + '\nBuild Url: ' + "${BUILD_URL}" + '\nStatus: Failed', subject: "${application}" + ' -- ' + "${packageName}" + ' -- ' + "${environment}"+ ' -- Status: Passed', to: "${emailList}"
    }
}

def emailUsers(isBuildFailure, application, packageName, environment, browser, testUrl, buildUrl, emailList, subject, message, attachmentsPattern) {
    emailext attachmentsPattern: "${attachmentsPattern}", body: "${env.JOB_BASE_NAME}" + '\nApplication: '+ "${application}" + '\nModule: ' + "${packageName}" + '\nEnvironment: ' + "${environment}" + '\nBrowser: ' + "${browser}" + '\nTestUrl: ' + "${testUrl}" + '\nBuild Url: ' + "${BUILD_URL}" + '\nStatus: ' + "${message}", subject: "${subject}", to: "${emailList}"
}


def emailCatalogUsers(isBuildFailure, application, packageName, environment, browser, testUrl, buildUrl, emailList) {
  if (!isBuildFailure) {
    emailext attachmentsPattern: '**/Catlog_WWW_NEW.xls', body: "Dear All,\n\nThe automation script has tested Search and Browse functionality on WWW Production site\n\nNo New Issues Found.\n\nPlease find Test Result for more information.\n\n\n${env.JOB_BASE_NAME}" + '\nApplication: '+ "${application}" + '\nModule: ' + "${packageName}" + '\nEnvironment: ' + "${environment}" + '\nBrowser: ' + "${browser}" + '\nTestUrl: ' + "${testUrl}" + '\nBuild Url: ' + "${BUILD_URL}" + '\nStatus: Passed',subject: 'WWW Catalog Test Passed' , to: "${emailList}"
    } else {
      emailext attachmentsPattern: '**/Catlog_WWW_NEW.xls', body: "WWW Catalog Test Failed. Please check console log. \n\n\n ${env.JOB_BASE_NAME}" + '\nApplication: '+ "${application}" + '\nModule: ' + "${packageName}" + '\nEnvironment: ' + "${environment}" + '\nBrowser: ' + "${browser}" + '\nTestUrl: ' + "${testUrl}" + '\nBuild Url: ' + "${BUILD_URL}" + '\nStatus: Failed',subject: 'WWW Catalog Test Failed', to: "${emailList}"
    }
}
def emailUsers(application, packageName, environment, browser, testUrl, buildUrl, jobUserId) {
  
      emailext body: "${env.JOB_BASE_NAME}" + '\nApplication: '+ "${application}" + '\nModule: ' + "${packageName}" + '\nEnvironment: ' + "${environment}" + '\nBrowser: ' + "${browser}" + '\nTestUrl: ' + "${testUrl}" + '\nBuild Url: ' + "${BUILD_URL}" + '\nStatus: Aborted', subject: "${application}" + ' -- ' + "${packageName}" + ' -- ' + "${environment}"+ ' -- Status: Aborted', to: "${jobUserId}"
    }

def setTimeToEdt() {
    sh "cp /usr/share/zoneinfo/EST5EDT /etc/localtime"
    //sh "echo \"America/New_York\" >  /etc/timezone"
    sh "date"
}

def installApk() {
  sh "apk --no-cache add ca-certificates fontconfig ttf-dejavu libpcap-dev dpkg"
}

def installMaven() {
  sh "mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -fsSL -o /tmp/apache-maven.tar.gz https://apache.osuosl.org/maven/maven-3/3.5.4/binaries/apache-maven-3.5.4-bin.tar.gz \
  && echo \"ce50b1c91364cb77efe3776f756a6d92b76d9038b0a0782f7d53acf1e997a14d  /tmp/apache-maven.tar.gz\" | sha256sum -c - \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn"
  sh "set MAVEN_HOME=/usr/share/maven"
  sh "set MAVEN_CONFIG=\$HOME/.m2"
}

def checkoutFromSvn(repo) {
  container('maven353'){
    checkout([$class: 'SubversionSCM',
    additionalCredentials: [],
    excludedCommitMessages: '',
    excludedRegions: '',
    excludedRevprop: '',
    excludedUsers: '',
    filterChangelog: false,
    ignoreDirPropChanges: false,
    includedRegions: '',
    locations: [[cancelProcessOnExternalsFail: false,
    credentialsId: "${svnId}",
    depthOption: 'infinity',
    ignoreExternalsOption: false,
    local: '.',
    remote: "${repo}"]],
    quietOperation: true,
    workspaceUpdater: [$class: 'UpdateUpdater']])
  }
}
return this;
