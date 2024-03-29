package se.techinsight.ssh

import com.jcraft.jsch.Channel
import com.jcraft.jsch.JSch
import com.jcraft.jsch.JSchException
import com.jcraft.jsch.Session
import org.apache.commons.io.output.CloseShieldOutputStream
import org.apache.tools.ant.util.TeeOutputStream

class RemoteSession extends RemoteSessionData {

    private Session session = null
    private final JSch jsch = null

    RemoteSession(JSch jsch) {
        this.jsch = jsch
    }

    def connect() {
        if (session == null || !session.connected) {
            disconnect()
            if (host == null) {
                throw new RuntimeException('Host is required.')
            }
            if (username == null) {
                throw new RuntimeException('Username is required.')
            }
            if (password == null) {
                throw new RuntimeException('Password is required.')
            }
            session = jsch.getSession(username, host, port)
            session.password = password
            println ">>> Connecting to '$host' as '$username'"
            try {
                session.connect()
            } catch (JSchException e) {
                println e.message == "Auth fail" ? "Credentials are incorrect!!!" : e
                throw new RuntimeException('Username or Password are incorrect')
            }
        }
    }

    def disconnect() {
        if (session?.connected) {
            try {
                session.disconnect()
            } catch (Exception e) {
            } finally {
                println "<<< Disconnected from $host"
            }
        }
    }

    def reconnect() {
        disconnect()
        connect()
    }

    CommandOutput exec(String cmd) {
        connect()
        catchExceptions {
            awaitTermination(executeCommand(cmd))
        }
    }

    private ChannelData executeCommand(String cmd) {
        println "> $cmd"
        def channel = session.openChannel('exec')
        def savedOutput = new ByteArrayOutputStream()
        def systemOutput = new CloseShieldOutputStream(System.out)
        def output = new TeeOutputStream(savedOutput, systemOutput)
        channel.command = cmd
        channel.outputStream = output
        channel.extOutputStream = output
        channel.setPty(true)
        channel.connect()
        new ChannelData(channel: channel, output: savedOutput)
    }

    class ChannelData {
        ByteArrayOutputStream output
        Channel channel
    }

    private CommandOutput awaitTermination(ChannelData channelData) {
        Channel channel = channelData.channel
        try {
            def thread = null
            thread = new Thread() {
                void run() {
                    while (!channel.isClosed()) {
                        if (thread == null) {
                            return
                        }
                        try {
                            sleep(1000)
                        } catch (Exception e) {
                            // ignored
                        }
                    }
                }
            }
            thread.start()
            thread.join(0)
            if (thread.isAlive()) {
                thread = null
                return failWithTimeout()
            } else {
                int ec = channel.exitStatus
                return new CommandOutput(ec, channelData.output.toString())
            }
        } finally {
            channel.disconnect()
        }
    }

    private CommandOutput catchExceptions(Closure cl) {
        try {
            return cl()
        } catch (JSchException e) {
            return failWithException(e)
        }
    }

    private CommandOutput failWithTimeout() {
        println 'Session timeout!'
        new CommandOutput(-1, 'Session timeout!')
    }

    private CommandOutput failWithException(Throwable e) {
        println "Caught exception: ${e.message}"
        new CommandOutput(-1, e.message, e)
    }
}
