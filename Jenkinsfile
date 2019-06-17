pipeline {
  agent {
    docker {
      image 'maven:3-alpine'
      args 'sh  -v /root/.m2:/root/.m2'
    }

  }
  stages {
    stage('Clone') {
      steps {
        git(branch: 'master', url: 'https://github.com/LeoNiedermeier/io.github.leoniedermeier.jenkins.demo.git')
      }
    }
    stage('Build') {
      steps {
        sh 'mvn -B -DskipTests clean package'
      }
    }
  }
}