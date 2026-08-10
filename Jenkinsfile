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

        stage('Check Docker') {
            steps {
                bat 'where docker'
                bat 'docker --version'
            }
        }


        stage('Firebase Setup') {
            steps {
                withCredentials([
                    file(
                        credentialsId: 'firebase-service-account',
                        variable: 'FIREBASE_KEY'
                    )
                ]) {
                    bat '''
                        if not exist src\\main\\resources\\firebase mkdir src\\main\\resources\\firebase
                        copy /Y "%FIREBASE_KEY%" "src\\main\\resources\\firebase\\serviceAccountKey.json"
                    '''
                }
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

       stage('Docker Build') {
           steps {
               bat 'docker build -t kellytechcoder/bellefood-backend:8 .'
               bat 'docker tag kellytechcoder/bellefood-backend:8 kellytechcoder/bellefood-backend:latest'
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
                   bat 'docker push kellytechcoder/bellefood-backend:8'
                   bat 'docker push kellytechcoder/bellefood-backend:latest'
               }
           }
       }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }

        success {
            echo 'BelleFood backend pipeline completed successfully!'
        }

        failure {
            echo 'BelleFood backend pipeline failed.'
        }
    }
}