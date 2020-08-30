
    node () {

       
        stage("Read Constants and Shared Library") {
            script {
                constants = evaluate readTrusted('jenkins_pipeline/pipeline_constants.groovy')
                sharedLib = evaluate readTrusted('jenkins_pipeline/common_lib.groovy')
                gitId = "${constants.gitId}"
            
            }
        }

        stage("Get Package Names from SCM") {
                checkout([
                        $class                           : 'GitSCM',
                        branches                         : [[name: '*/master']],
                        doGenerateSubmoduleConfigurations: false,
                        extensions                       : [],
                        submoduleCfg                     : [],
                        userRemoteConfigs                : [[credentialsId: "${gitId}", url: 'https://github.com/rakeshsun/devops-project.git']]])
            }
}

