import org.example.utils.Helpers

def call(String imageName) {
    def helper = new Helpers(this)

    helper.startStage('Deploy')
    helper.safeSh("""
        echo "Deploying container..."
        docker stop jenkins-app || true
        docker rm jenkins-app || true
        docker run -d -p 9000:8080 --name jenkins-app ${imageName}:${env.BUILD_NUMBER}
        echo "App is running on: http://<your-server-ip>:9000"
    """)
    helper.endStage('Deploy')
}
