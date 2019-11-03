package ssh

import com.aestasit.infrastructure.ssh.SshOptions
import com.aestasit.infrastructure.ssh.dsl.SshDslEngine
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import se.techinsight.db.DbHelper

import static com.aestasit.infrastructure.ssh.DefaultSsh.*

class SshoogrFeaturesTest {

    SshOptions options
    SshDslEngine engine

    /*
            options.execOptions {
//            prefix = 'sudo'
            trustUnknownHosts = true //StrictHostKeyChecking=no
            showCommand = false
            logger = sf4j()
        }
          remoteSession('funker:funker@192.168.178.100:21598') {
            exec 'uname -a'
         }
     */

    @BeforeEach
    void setUp() {
        options = new SshOptions()
        options.logger = sf4j() //ansi()

        options.defaultHost = '192.168.178.100'
        options.defaultUser = 'funker'
        options.defaultPassword = 'funker'
        options.defaultPort = 21598

        options.reuseConnection = true
        options.trustUnknownHosts = true

        options.verbose = true

        options.execOptions.showOutput = false


        engine = new SshDslEngine(options)
    }

    @Disabled("Not work need to add 'trustUnknownHosts = true'")
    @Test
    void simpleTest1() {
        remoteSession('funker:funker@192.168.178.100:21598') {
            exec 'uname -a'
        }
    }

    @Test
    void simpleTest2() {
        engine.remoteSession {
            exec 'pwd'
            exec 'uptime'
            exec 'ping -c 1 google.com'
            exec 'groovy --version'
            exec 'groovy -e "println \'Hello, Remote!\'"'
        }
    }

    @Test
    void fileTest() {
        String hostSrt
        engine.remoteSession {
            def file = '/tmp/test.file'
            hostSrt = remoteFile("/etc/hosts").text
            remoteFile(file).touch()
            remoteFile(file).setText(" \t  nononon asda asdasda \nasdadf ada \n adfd   ", true)
            def text = remoteFile(file).text
            exec "cat $file"

//            remoteDir("/var/log/nginx")
//            exec 'rm -rf /tmp/*'
//            exec 'touch /var/lock/my.pid'
//            remoteFile('/var/my.conf').text = "enabled=true"

            suffix(">> output.log") {
                exec 'ls -la'
                exec 'df -h'
                exec 'date'
            }
        }


        println("Total size is " + hostSrt.split("\n").size())
    }

    @Test
    void addSuffixTest() {
        String output = captureOutput {
            engine.remoteSession {
                suffix(">> output.log") {
                    exec 'ls -la'
                    exec 'df -h'
                    exec 'date'
                }
                disconnect()
            }
        }

        assert output.contains('ls -la >> output.log')
    }

    @Test
    void useTunnelAndDbTest() {
        //  map 192.168.178.100:5432 to localhost:randomPort

//      def dbCred = ["host": "localhost", "user": "u_sonar", "password": "u_sonar", "port": "5432", "db": "sonar"]
        def dbCred = ["user": "u_sonar", "password": "u_sonar"]

        engine.remoteSession {
            tunnel(options.defaultHost, 5432) { int localPort ->
                def helper = new DbHelper(dbCred << ["port": localPort])
                def res = helper.getDbVersion()
                logger.info("Result " + res)
                assert (res['version'] as String).contains('PostgreSQL 9.5.19')

                def users = helper.getAllUsers()
                logger.info("Users " + users)
            }
        }
    }


    static String captureOutput(Closure cl) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream()
        PrintStream ps = new PrintStream(baos)
        PrintStream old = System.out
        System.out = ps
        try {
            cl()
        } finally {
            System.out.flush()
            System.out = old
        }
        System.out.println baos.toString()
        baos.toString()
    }

}



