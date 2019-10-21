
package se.techinsight.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import se.techinsight.App;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class PrintInfoTest {

    @Test
    void appHasAGreeting(TestInfo testInfo) {
        log.info("Test name '{}'", testInfo.getDisplayName());
        App classUnderTest = new App();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }

    @Tags({
        @Tag("aaa"),
        @Tag("bbb")
    })
    @DisplayName("Get Test info for some purposes")
    @Test
    void getTestInfo(TestInfo testInfo) { // Automatically injected
        log.info("Display Name '{}'",testInfo.getDisplayName());
        log.info("Test Method '{}'",testInfo.getTestMethod());
        log.info("Test Class '{}'", testInfo.getTestClass());
        log.info("Tags '{}'",testInfo.getTags());

//        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
    }

}
