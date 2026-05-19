pipeline {
    agent any
    tools {
        maven 'Maven-3.9'
    }
    environment {
        DOCKER_IMAGE = 'wuzhi456/teedy-app'  
        DOCKER_TAG = "${env.BUILD_NUMBER}"
    }
    stages {
        stage('Build & Test & Site') {
            steps {
                sh 'pwd'
                sh 'ls'
                sh 'mvn clean test jacoco:report jacoco:report-aggregate site -Dmaven.test.failure.ignore=true'
            }
        }
        stage('PMD') {
            steps {
                sh 'mvn pmd:pmd'
            }
        }
        stage('Javadoc') {
            steps {
                sh 'mvn javadoc:javadoc'
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}")
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', DOCKER_HUB_CREDENTIALS) {
                        docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").push()
                        docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").push('latest')
                    }
                }
            }
        }

        stage('Run Containers') {
            steps {
                script {
                    sh 'docker stop teedy-8082 || true'
                    sh 'docker rm teedy-8082 || true'
                    sh 'docker stop teedy-8083 || true'
                    sh 'docker rm teedy-8083 || true'
                    sh 'docker stop teedy-8084 || true'
                    sh 'docker rm teedy-8084 || true'

                    docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").run('--name teedy-8082 -d -p 8082:8080')
                    docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").run('--name teedy-8083 -d -p 8083:8080')
                    docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").run('--name teedy-8084 -d -p 8084:8080')
                }
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: '**/target/site/**/*.*', fingerprint: true
            archiveArtifacts artifacts: '**/target/**/*.jar', fingerprint: true
            archiveArtifacts artifacts: '**/target/**/*.war', fingerprint: true
            junit '**/target/surefire-reports/*.xml'
        }
    }
}