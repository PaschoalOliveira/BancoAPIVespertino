pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                def dockerHome = tool 'myDocker'
	        	env.PATH = "${dockerHome}/bin:${env.PATH}"
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