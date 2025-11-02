def call(String imageName) {
    stage('Docker Build & Push') {
        sh "docker build -t ${imageName}:${env.BUILD_NUMBER} ."

        withCredentials([string(credentialsId: 'dockerhub-pass', variable: 'DOCKERHUB_PASS')]) {
            sh '''
                echo "$DOCKERHUB_PASS" | docker login -u abdomagdy --password-stdin
                docker info | grep Username
            '''
        }

        sh """
            docker push ${imageName}:${env.BUILD_NUMBER}
            docker tag ${imageName}:${env.BUILD_NUMBER} ${imageName}:latest
            docker push ${imageName}:latest
        """
    }
}
