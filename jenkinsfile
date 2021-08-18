pipeline {
    agent any

    stages {
	     stage('Initialize'){
	        def dockerHome = tool 'myDocker'
	        env.PATH = "${dockerHome}/bin:${env.PATH}"
	    }
        stage('Build') {
            steps {
                echo 'Building..'
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