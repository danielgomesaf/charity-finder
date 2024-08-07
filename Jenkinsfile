pipeline {
    agent any
    stages {
        stage('Git-Checkout') {
            steps {
                echo "Checking out from git repo";
                git credentialsId: 'ea84d5c4-ccbb-4d19-90d6-8fe77bb5ebae', url: 'https://github.com/danielgomesaf/charity-finder.git'
            }
        }

        stage('Build') {
            steps {
                echo "Building the checked-out project";
                bat 'Build.bat'
            }
        }

        stage('Unit-Test') {
            steps {
                echo "Running JUnit Tests";
                bat 'Unit.bat'
            }
        }

        stage('Quality-Gate') {
            steps {
                echo "Verifying Quality Gates";
                bat 'Quality.bat'
            }
        }

        stage('Deploy') {
            steps {
                echo "Deploying to stage environment for more tests";
                bat 'Deploy.bat'
            }
        }
    }
    post {
        always {
            echo 'This will always run'
        }
        success {
            echo 'This will run only if successful'
        }
        failure {
            echo 'This will run only if failed'
        }
        unstable {
            echo 'This will run only if the run was marked as unstable'
        }
        changed {
            echo 'This will run only if the state of the pipeline has changed'
            echo 'For example, if the pipeline was previously failing but is now successful'
        }
    }
}
