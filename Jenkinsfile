pipeline {
    options {
        buildDiscarder(logRotator(numToKeepStr: '5', daysToKeepStr: '90', artifactNumToKeepStr: '5'))
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
            when { branch 'master' }
            steps {
                unstash 'dccweb'
                sh 'mvn verify'
            }
        }
        stage('Sonar') {
            when { branch 'master' }
            steps {
                unstash 'dccweb'
                sh 'mvn sonar:sonar'
            }
        }
        stage('Archive') {
            when { branch 'master' }
            steps {
                unstash 'dccweb'
                archiveArtifacts artifacts: 'dcc-webapp/target/*.jar', fingerprint: true
                archiveArtifacts artifacts: 'dcc-webapp/target/*.zip', fingerprint: true
                archiveArtifacts artifacts: 'dcc-webapp/target/*.tar.gz', fingerprint: true
            }
        }
        stage('release') {
            when { branch 'develop' }
            steps {
                checkout([
                        $class: 'GitSCM',
                        branches: [[name: env.BRANCH_NAME]],
                        extensions: [[$class: 'LocalBranch', localBranch: env.BRANCH_NAME]],
                        userRemoteConfigs: [[credentialsId: 'GITHUB', url: 'https://github.com/shawnbelton/dccweb.git']]
                ])

                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'GITHUB', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                    sh "git checkout -B develop"
                    sh "mvn -B -Dgit.user=$USERNAME -Dgit.password='$PASSWORD' -DpushReleases=false jgitflow:release-start"
                    sh "mvn -B -Dgit.user=$USERNAME -Dgit.password='$PASSWORD' -DpushReleases=true jgitflow:release-finish"
                }
            }
        }
    }
}
