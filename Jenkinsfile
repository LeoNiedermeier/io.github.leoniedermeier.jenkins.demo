pipeline {
  agent {
    docker {
      image 'maven:3-alpine'
      args '-v /root/.m2:/root/.m2 --network container:sonarnet'
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
                sh 'mvn -version'
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') { 
            steps {
                sh 'mvn test' 
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
              sh 'mvn -X -DskipTests sonar:sonar'
          }}
  }
}
