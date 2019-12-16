package ubiquiti

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.junit.jupiter.api.Test

class UnifiTest {

    private def prop = [
            'Accept'          : 'application/json',
            'x-requested-with': 'XMLHttpRequest'
    ]
    private def host = "https://www.ui.com/download/?platform=unifi"

/*
curl -s -X GET 'https://www.ui.com/download/?platform=unifi' \
-H 'accept: application/json' \
-H 'x-requested-with: XMLHttpRequest' --compressed | jq .
 */


    @Test
    void printAvailableSoftwareVersionsOnServer() {
        def body = host.toURL().getText(requestProperties: prop)
        def parsedJson = new JsonSlurper().parseText(body)['downloads']
        def software = parsedJson.findAll { it.category__slug == 'software' }.sort { it.date_published }.reverse()
        def firmware = parsedJson.findAll { it.category__slug == 'firmware' }.sort { it.date_published }.reverse()

        printMy(software.findAll { it['description'].contains("Ubuntu") }, "--software--")
        printMy(firmware.findAll { it['products'].contains("AC-PRO") }, "--firmware--")
    }


    static void printMy(def values, def message, boolean prettyPrint = false) {
        def output = new JsonOutput()
        println("=========" * 10)
        println message
        for (def t : values) {
            println("${t.date_published} - ${t.version} - ${t.name}")
            if (prettyPrint) {
                def jsonString = output.toJson(t)
                println(output.prettyPrint(jsonString))
            }
        }
    }
}
