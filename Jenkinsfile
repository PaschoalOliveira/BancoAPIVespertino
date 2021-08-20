pipeline {
    agent any
   
    stages {
        stage('Test') {
            agent {
                docker { image "hello-world" }
            }
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo  'Deploying....'
            }
        }
    }
}
