pipeline {
    options {
        buildDiscarder(logRotator(numToKeepStr: '10', daysToKeepStr: '90', artifactNumToKeepStr: '5'))
    }
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
                junit '**/target/surefire-reports/*.xml'
                stash includes: '**/target/*.jar', name: 'dccweb'
            }
        }
        stage('Integration') {
            steps {
                unstash 'dccweb'
                sh 'mvn verify'
            }
        }
        stage('Sonar') {
            steps {
                unstash 'dccweb'
                sh 'mvn sonar:sonar'
            }
        }
        stage('Archive') {
            steps {
                unstash 'dccweb'
                archiveArtifacts artifacts: 'dcc-webapp/target/*.jar', fingerprint: true
                archiveArtifacts artifacts: 'dcc-webapp/target/*.zip', fingerprint: true
                archiveArtifacts artifacts: 'dcc-webapp/target/*.tar.gz', fingerprint: true
            }
        }
    }
}
