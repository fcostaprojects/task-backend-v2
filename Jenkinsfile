pipeline {
    agent any
    stages {
        stage("Build backend") {
            steps {
                sh './mvnw clean package -DskipTests=true'
            }
        }
        stage("Unit tests") {
            steps {
                sh './mvnw test'
            }
        }
    }
}