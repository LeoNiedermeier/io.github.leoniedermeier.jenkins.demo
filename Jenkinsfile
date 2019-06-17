pipeline {
    agent any
    stages {
        stage ('Clone') {
            steps {
                git branch: 'master', url: "https://github.com/LeoNiedermeier/io.github.leoniedermeier.jenkins.demo.git"
            }
        }
    }
}
