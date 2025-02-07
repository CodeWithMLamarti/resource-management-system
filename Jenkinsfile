pipeline{
    agent any
    tools {
        maven 'Maven_3.9.9'
    }
    stages{
        stage("Build the artifact"){
            steps{
                sh 'mvn clean package'
            }
        }

        stage("Build the image"){
            steps{
                sh 'docker build -t medlmt/ressource-managment-app:1.0'
            }
        }

        stage("push the image"){
            steps{
                sh 'docker push medlmt/ressource-managment-app:1.0'
            }
        }
        stage("Deploy the image"){
            steps{
                echo 'App deployed Successfully!'
            }
        }
    }
    post{
        success{
            echo "========pipeline executed successfully ========"
        }
        failure{
            echo "========pipeline execution failed========"
        }
    }
}
