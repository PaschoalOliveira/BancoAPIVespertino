pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                def dockerHome = tool 'docker'
	        	ENV PATH = "${dockerHome}/bin:${env.PATH}"
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}