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
  environment {
        //Use Pipeline Utility Steps plugin to read information from pom.xml into env variables
        IMAGE = readMavenPom().getArtifactId()
        VERSION = readMavenPom().getVersion()
        BUILD_RELEASE_VERSION = readMavenPom().getVersion().replace("-SNAPSHOT", ".1.1")
        IS_SNAPSHOT = readMavenPom().getVersion().endsWith("-SNAPSHOT")
        GIT_TAG_COMMIT = sh(script: 'git describe --tags --always', returnStdout: true).trim()
        // writeMavenPom().setVersion("4.1.2")
        NEW_VERSION = readMavenPom().getVersion()
        JIRA_SITE='JIRA'
        GIT_COMMIT_MSG = sh(script: 'git log -1 --oneline')
        GIT_COMMIT_AUTHOR = sh(script: 'git log --format="medium" -1 ${GIT_COMMIT}')
        GIT_COMMIT_PRETTY = sh(script: 'git log -1 --pretty=%B' , returnStdout: true).trim()
        // commitHash = checkout(scm).GIT_COMMIT?
        // sh "echo 'Commit hash is: ${commitHash}'"
    }
            steps {
             echo " Project version is ${VERSION}"
                echo "Artifact id is ${IMAGE}"
                sh 'mvn -version'
                sh 'mvn -B -DskipTests fr.jcgay.maven.plugins:buildplan-maven-plugin:list clean package'
            }
    }
    stage('Test') { 
            steps {
                sh 'mvn -B fr.jcgay.maven.plugins:buildplan-maven-plugin:list test' 
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml' 
                }
            }
        }
        
        stage('Scan') {
          steps {
          
              echo 'Scanning...'
              sh 'mvn fr.jcgay.maven.plugins:buildplan-maven-plugin:list sonar:sonar -Dsonar.host.url=http://127.0.0.1:9000'
          }}
  stage('Deploy') {
     steps {
         echo 'Hello Jenkins!'
     }
  }
  
  
  
  }
}
