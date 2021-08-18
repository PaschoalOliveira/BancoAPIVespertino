pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
		RUN apk update && apk add bash
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
