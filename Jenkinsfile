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

        stage('Docker Build') {
            steps {
                bat 'docker build -t %DOCKER_IMAGE%:%IMAGE_TAG% .'
                bat 'docker tag %DOCKER_IMAGE%:%IMAGE_TAG% %DOCKER_IMAGE%:latest'
            }
        }

       stage('Docker Hub Check') {
           steps {
               bat '''
                   docker version
                   docker info
                   curl -I https://registry-1.docker.io/v2/
               '''
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