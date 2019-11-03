package se.techinsight.db;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WorkWithDbTest {

    Map<String, String> dbCredentials = new HashMap<String, String>() {{
        put("host", "192.168.178.100");
        put("user", "u_sonar");
        put("password", "u_sonar");
        put("db", "sonar");
        put("port", "5432");
    }};


    @Disabled
    @Test
    void nameTest() {
        DbHelper db = new DbHelper(dbCredentials);
        Map<String, String> dbVersion = db.getDbVersion();
        System.out.println(dbVersion);

        assertTrue(dbVersion.toString().contains("version=PostgreSQL 9.5.19"));
    }

    @Test
    void nameTest2() {

        dbCredentials.put("host", "localhost");
        System.out.println(dbCredentials);

    }


}
