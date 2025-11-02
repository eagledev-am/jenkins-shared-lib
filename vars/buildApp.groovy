def call() {
    stage('Build') {
        echo "Build Number: ${env.BUILD_NUMBER}"
        sh "mvn clean package"
    }
}
