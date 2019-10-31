package ssh

import org.junit.jupiter.api.Test
import se.techinsight.ssh.SshDslEngine

class SshEngineTest {

    def connectionString = 'santa:santa@192.168.178.100:21598'

    @Test
    void simpleTest() {
        new SshDslEngine().remoteSession {
            url = connectionString
            exec 'groovy --version'
            exec 'groovy -e "println \'Hello, Remote!\'"'
        }
    }

    @Test
    void extendedTest() {

        new SshDslEngine().remoteSession {
            url = connectionString
            connect()

            if (exec('apt list --installed | grep groovy').exitStatus != 0) {
                exec 'apt install groovy'
            } else {
                println "Groovy already installed"
            }
            disconnect()
            connect()
            def result = exec 'groovy -e "println \'Hello, Remote!\'"'
            if (!result.output.contains('Hello')) {
                throw new RuntimeException('Command failed!')
            }
            disconnect()
        }
    }
}
