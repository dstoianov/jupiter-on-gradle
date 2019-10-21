package se.techinsight.test.feature.disableif;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;


// more examples here
// https://www.baeldung.com/junit-5-conditional-test-execution


class DisabledByConditionTest {

    @Test
//    @DisabledOnOs({OS.WINDOWS, OS.MAC})
    @EnabledOnOs({OS.WINDOWS, OS.MAC})
    void shouldRunBothWindowsAndMac() {
        // some logic here
    }


    @Test
    @DisabledOnOs(OS.LINUX)
     void shouldNotRunAtLinux() {
        //...
    }


    @Test
    @EnabledOnJre({JRE.JAVA_10, JRE.JAVA_11})
     void shouldOnlyRunOnJava10And11() {
        //...
    }

    @Test
    @DisabledOnJre(JRE.OTHER)
    void thisTestOnlyRunsWithUpToDateJREs() {
        // this test will only run on Java 8, 9, 10, and 11.
    }


    @Test
    @EnabledIfSystemProperty(named = "java.vm.vendor", matches = "Oracle.*")
     void onlyIfVendorNameStartsWithOracle() {
        //...
    }


    @ThisTestWillOnlyRunAtLinuxAndMacWithJava9Or10Or11
     void someSuperTestMethodHere() {
        // this method will run with Java9, 10, 11 and Linux or macOS.
    }


}
