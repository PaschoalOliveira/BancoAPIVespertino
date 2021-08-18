pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
				RUN curl -fsSLO https://get.docker.com/builds/Linux/x86_64/docker-17.04.0-ce.tgz \
				  && tar xzvf docker-17.04.0-ce.tgz \
				  && mv docker/docker /usr/local/bin \
				  && rm -r docker docker-17.04.0-ce.tgz
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