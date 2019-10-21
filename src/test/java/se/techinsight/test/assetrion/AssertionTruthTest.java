package se.techinsight.test.assetrion;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class AssertionsExample {

    @Test
    void name123() {
//        System.setProperty("com.google.common.truth.disable_stack_trace_cleaning", "true");

        String string = "awesome";
        assertThat(string).startsWith("aawe");
        assertWithMessage("Without me, it's just aweso").that(string).contains("me");


    }
}
