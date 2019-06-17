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
                sh 'mvn -version'
                sh 'mvn -B -DskipTests buildplan:list clean package'
            }
    }
    stage('Test') { 
            steps {
                sh 'mvn -B buildplan:list test' 
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
              sh 'mvn buildplan:list sonar:sonar -Dsonar.host.url=http://127.0.0.1:9000'
          }}
  stage('Deploy') {
     steps {
         echo 'Hello Jenkins!'
     }
  }
  
  
  
  }
}
