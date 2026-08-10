pipeline {

    agent any

    environment {
        DOCKER_IMAGE = 'kellytechcoder/bellefood-backend'
        IMAGE_TAG = "${BUILD_NUMBER}"
    }

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

       stage('Docker Check') {
           steps {
               bat 'where docker'
               bat 'docker --version'
               bat 'docker ps'
           }
       }

        stage('Docker Push') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-credentials',
                        usernameVariable: 'DOCKER_USERNAME',
                        passwordVariable: 'DOCKER_PASSWORD'
                    )
                ]) {
                    bat 'docker login -u %DOCKER_USERNAME% -p %DOCKER_PASSWORD%'
                    bat 'docker push %DOCKER_IMAGE%:%IMAGE_TAG%'
                    bat 'docker push %DOCKER_IMAGE%:latest'
                }
            }
        }
    }

    post {
        success {
            echo 'BelleFood backend pipeline completed successfully!'
        }

        failure {
            echo 'BelleFood backend pipeline failed.'
        }
    }
}