// Einfaches Jenkinsfile 
 pipeline {
  agent {
    docker {
      image 'maven:3-alpine'
      args '-v /root/.m2:/root/.m2 --network=host '
    }
  }
  options {
    skipStagesAfterUnstable()
  }
  
  stages {
    stage('Clone') {
      steps {
        checkout scm
        //git(branch: 'master', url: 'https://github.com/LeoNiedermeier/io.github.leoniedermeier.jenkins.demo.git')
      }
    }
  
    stage('Maven: clean deploy') {
      steps {
        configFileProvider([configFile(fileId: 'jenkins-maven-settings', variable: 'MAVEN_SETTINGS')]) {
          echo "${env.BRANCH_NAME}"
          //sh 'printenv'
          sh "mvn -s ${MAVEN_SETTINGS}  fr.jcgay.maven.plugins:buildplan-maven-plugin:list-phase   -Dbuildplan.tasks=clean,deploy"
          sh "mvn -s ${MAVEN_SETTINGS} -Drevision=${BRANCH_NAME}-X-SNAPSHOT clean deploy"
        }
      }
      post {
        always {
          junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml' 
        }
      }
    }  
  }
}
