package se.techinsight.test.assetrion;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

class AssertionTruthTest {

    private List<String> bigList =
        Arrays.asList("guava", "dagger", "truth", "auto", "caliper", "raisin", "obs", "opsys");
    private List<String> smallList = Arrays.asList("rcba", "obs", "opsys", "dipo");

    @Test
    void name123() {
        String string = "awesome";
        assertThat(string).startsWith("awe");
        assertWithMessage("Without me, it's just aweso").that(string).contains("me");
    }

    @Test
    void listInListTest() {
        assertThat(bigList)
            .containsAtLeastElementsIn(Arrays.asList("obs", "guava", "truth"));
    }

    @Test
    void elementInInListTest() {
        assertThat(bigList)
            .contains("obs");
    }

    @Test
    void listInInListExactlyInAnyOrderTest() {
        assertThat(smallList)
            .containsExactly("dipo", "obs", "rcba", "opsys");
    }
}
