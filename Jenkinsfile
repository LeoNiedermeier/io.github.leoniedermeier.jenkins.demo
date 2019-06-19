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
        git(branch: 'master', url: 'https://github.com/LeoNiedermeier/io.github.leoniedermeier.jenkins.demo.git')
      }
    }
  
    stage('Build') {
      steps {
        configFileProvider([configFile(fileId: 'jenkins-maven-settings', variable: 'MAVEN_SETTINGS')]) {
          sh 'mvn -DskipTests clean install'
        }
      }
      post {
        always {
          junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml' 
        }
      }
    }
  
    stage('Test') { 
      steps {
        configFileProvider([configFile(fileId: 'jenkins-maven-settings', variable: 'MAVEN_SETTINGS')]) {
          sh 'mvn -s $MAVEN_SETTINGS test'
        }
      }
      post {
        always {
          junit 'target/surefire-reports/*.xml' 
        }
      }
    }
    
    stage('Sonar Analysis') {
      steps {
        configFileProvider([configFile(fileId: 'jenkins-maven-settings', variable: 'MAVEN_SETTINGS')]) {
          sh 'mvn -s $MAVEN_SETTINGS sonar:sonar'
        }
      }
    }
  
    stage('Deploy') {
      steps {
       configFileProvider([configFile(fileId: 'jenkins-maven-settings', variable: 'MAVEN_SETTINGS')]) {
          sh 'mvn -s $MAVEN_SETTINGS  org.apache.maven.plugins:maven-deploy-plugin:3.0.0-M1:deploy'
        } 
      }
    }  
  }
}
