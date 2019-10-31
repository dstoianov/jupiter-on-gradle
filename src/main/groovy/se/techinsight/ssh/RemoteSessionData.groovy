package se.techinsight.ssh

import java.util.regex.Pattern

class RemoteSessionData {

    static final int DEFAULT_SSH_PORT = 22
    static final Pattern SSH_URL = ~/^(([^:\@]+)(:([^\@]+))?\@)?([^:]+)(:(\d+))?$/

    String host = null
    int port = DEFAULT_SSH_PORT
    String username = null
    String password = null


    def setUrl(String url) {
        def matcher = SSH_URL.matcher(url)
        if (matcher.matches()) {
            host = matcher.group(5)
            port = matcher.group(7).toInteger()
            username = matcher.group(2)
            password = matcher.group(4)
        } else {
            throw new RuntimeException("Unknown URL format: $url")
        }
    }
}


