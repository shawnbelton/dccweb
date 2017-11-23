pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('Integration') {
            steps {
                sh 'mvn verify'
            }
        }
        stage('Sonar') {
            steps {
                sh 'mvn sonar:sonar'
            }
        }
        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'dcc-webapp/target/*.jar', fingerprint: true
                archiveArtifacts artifacts: 'dcc-webapp/target/*.zip', fingerprint: true
                archiveArtifacts artifacts: 'dcc-webapp/target/*.tar.gz', fingerprint: true
            }
        }
    }
}
