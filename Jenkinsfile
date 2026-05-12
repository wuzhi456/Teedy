pipeline {
    agent any
    tools {
        maven 'Maven-3.9'
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