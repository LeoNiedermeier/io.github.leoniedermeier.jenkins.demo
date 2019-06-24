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
    buildDiscarder(logRotator(numToKeepStr: '5'))
  }
  
  stages {
    stage('Clone') {
      steps {
        checkout scm
      }
    }
  
    stage('Maven: clean deploy') {
      steps {
        configFileProvider([configFile(fileId: 'jenkins-maven-settings', variable: 'MAVEN_SETTINGS')]) {
          sh "mvn -s ${MAVEN_SETTINGS}  fr.jcgay.maven.plugins:buildplan-maven-plugin:list-phase   -Dbuildplan.tasks=clean,deploy"
          // Mit CI fiendly versions:	
          sh "mvn -s ${MAVEN_SETTINGS} -Drevision=${BRANCH_NAME}-SNAPSHOT clean deploy"
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
