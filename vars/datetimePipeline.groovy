def call(Map config) {
    pipeline {
        agent any

        stages {
            stage('Checkout') {
                steps {
                    checkout scm
                }
            }

            stage('Build Docker Image') {
                steps {
                    script {
                        dockerImage = docker.build("${config.dockerRepo}:${config.imageTag}")
                    }
                }
            }

            stage('Test Docker Image') {
                steps {
                    script {
                        dockerImage.inside {
                            sh 'curl -f http://localhost:5000 || exit 1'
                        }
                    }
                }
            }

            stage('Push to DockerHub') {
                steps {
                    script {
                        docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-credentials') {
                            dockerImage.push()
                        }
                    }
                }
            }

            stage('Deploy Docker Image') {
                steps {
                    sh "docker run -d -p 5000:5000 ${config.dockerRepo}:${config.imageTag}"
                }
            }
        }
    }
}
