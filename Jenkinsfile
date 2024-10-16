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
        stage("Sonar analisis") {
            environment {
                scannerHome = tool 'sonar_scanner'
            }
            steps {
                withSonarQubeEnv('sonarcube') {
                    sh "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=deploy_backend -Dsonar.host.url=http://172.30.0.11:9000 -Dsonar.login=sqp_6552866669d838287abbb85cc0d90b7d601123ba -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/src/test/**,**models/**,**TasksBackendApplication.java"
                }
            }
        }
        stage("Quality gate") {
            steps {
                sleep(5)
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage('Deploy backend') {
            steps {
                deploy adapters: [tomcat9(credentialsId: 'tomcat', path: '', url: 'http://172.30.0.14:8080/')], contextPath: 'tasks', war: 'target/TasksBackend-0.0.1-SNAPSHOT.war'
            }
        }
    }
}

d