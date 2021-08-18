pipeline {
    agent any

	script{
		RUN apk update && apk add bash
	}
	
    stages {
        stage('Build') {
            steps {
		
		sh "${env.WORKSPACE}/../${env.JOB_NAME}@script/script.sh"
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
