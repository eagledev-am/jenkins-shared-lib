package org.example.utils

class Helpers implements Serializable {

    def script

    Helpers(script) {
        this.script = script
    }

    /** Log messages with a timestamp and stage context */
    def log(String message) {
        def timestamp = new Date().format("yyyy-MM-dd HH:mm:ss")
        script.echo "[${timestamp}] [INFO] ${message}"
    }

    /** Highlight stage start */
    def startStage(String stageName) {
        log("===== STARTING STAGE: ${stageName} =====")
    }

    /** Highlight stage end */
    def endStage(String stageName) {
        log("===== FINISHED STAGE: ${stageName} =====")
    }

    /** Run shell safely */
    def safeSh(String command) {
        try {
            script.sh command
        } catch (err) {
            log("Command failed: ${command}")
            throw err
        }
    }
}
