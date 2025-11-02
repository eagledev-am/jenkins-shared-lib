import org.example.utils.Helpers

def call() {
    def helper = new Helpers(this)

    helper.startStage('Build')
    helper.log("Build Number: ${env.BUILD_NUMBER}")
    helper.safeSh("mvn clean package")
    helper.endStage('Build')
}
