pipeline {
    agent any
    tools {
        maven 'Maven-3.9'   
    }
    stages {
        stage('Clean') {
            steps {
                sh 'mvn clean'
            }
        }
        stage('Compile') {
            steps {
                sh 'mvn compile'
            }
        }
        stage('Test & JaCoCo') {
            steps {
                sh 'pwd'
                sh 'ls'
                sh 'ls docs-core/target || true'
                sh 'ls docs-core/target/jacoco.exec || true'
                sh 'ls docs-web-common/target/jacoco.exec || true'
                sh 'ls docs-web/target/jacoco.exec || true'
                sh 'ls docs-core/target/surefire-reports || true'
                sh 'mvn test jacoco:report-aggregate'
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
        stage('Site') {
            steps {
                sh 'mvn site'
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