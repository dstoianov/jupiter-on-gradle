package se.techinsight.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class SimpleTest extends BaseTest {

    @DisplayName("Single test successful")
    @Test
    void testSingleSuccessTest() {
        log.info("Success");
    }

    @Test
    @Disabled("Not implemented yet")
    void testShowSomething() {
    }

    @Test
    void lambdaExpressions() {
        assertTrue(Stream.of(1, 2, 3).mapToInt(i -> i).sum() > 5, () -> "Sum should be greater than 5");
    }

    @Test
    void groupAssertions() {
        int[] numbers = {0, 1, 2, 3, 4};
        assertAll(
            "numbers",
            () -> assertEquals(numbers[0], 1),
            () -> assertEquals(numbers[3], 3),
            () -> assertEquals(numbers[4], 1)
        );
    }

    @Test
    void shouldThrowException() {
        Throwable exception = assertThrows(UnsupportedOperationException.class, () -> {
            throw new UnsupportedOperationException("Not supported");
        });
        assertEquals(exception.getMessage(), "Not supported");
    }

    @Test
    void assertThrowsException() {
        String str = null;
        assertThrows(IllegalArgumentException.class, () -> {
            Integer.valueOf(str);
        });
    }

//    @TestFactory
//    public Stream<DynamicTest> translateDynamicTestsFromStream() {
//        return in.stream()
//            .map(word ->
//                DynamicTest.dynamicTest("Test translate " + word, () -> {
//                    int id = in.indexOf(word);
//                    assertEquals(out.get(id), translate(word));
//                })
//            );
//    }
}
