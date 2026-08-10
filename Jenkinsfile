pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Kellytech-coder/bellefood-backend.git'
            }
        }

       stage('Build') {
           steps {
               bat 'mvnw.cmd clean package -DskipTests'
           }
       }

       stage('Test') {
           steps {
               bat 'mvnw.cmd test'
           }
       }
    }
}