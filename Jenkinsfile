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
