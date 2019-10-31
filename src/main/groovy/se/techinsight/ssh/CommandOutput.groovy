package se.techinsight.ssh

class CommandOutput {

    int exitStatus
    String output
    Throwable exception

    CommandOutput(int exitStatus, String output) {
        this.exitStatus = exitStatus
        this.output = output
    }

    CommandOutput(int exitStatus, String output, Throwable exception) {
        this.exitStatus = exitStatus
        this.output = output
        this.exception = exception
    }

}
