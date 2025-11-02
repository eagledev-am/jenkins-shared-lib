import org.example.utils.Helpers

def call(String imageName) {
    def helper = new Helpers(this)

    helper.startStage('Docker Build & Push')
    helper.safeSh("docker build -t ${imageName}:${env.BUILD_NUMBER} .")

    withCredentials([string(credentialsId: 'dockerhub-pass', variable: 'DOCKERHUB_PASS')]) {
        helper.safeSh('''
            echo "$DOCKERHUB_PASS" | docker login -u abdomagdy --password-stdin
            docker info | grep Username
        ''')
    }

    helper.safeSh("""
        docker push ${imageName}:${env.BUILD_NUMBER}
        docker tag ${imageName}:${env.BUILD_NUMBER} ${imageName}:latest
        docker push ${imageName}:latest
    """)

    helper.endStage('Docker Build & Push')
}
