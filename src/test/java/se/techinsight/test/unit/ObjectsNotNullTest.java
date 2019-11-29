package se.techinsight.test.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ObjectsNotNullTest {

    @DisplayName("Test assert NullPointerException")
    @Test
    void nameNoMessage() {
        String str = null;
        Throwable exception = assertThrows(NullPointerException.class, () -> Objects.requireNonNull(str));
        assertNull(exception.getMessage());
    }

    @Test
    void nameWithMessage() {
        String str = null;
        Throwable exception = assertThrows(NullPointerException.class, () -> Objects.requireNonNull(str, "str cannot be null"));
        assertEquals("str cannot be null", exception.getMessage());
    }

    @Test
    void nameWithMethodAndMessage() {
        String str = null;
        //useful when creating the message is expensive
        Throwable exception = assertThrows(NullPointerException.class, () -> Objects.requireNonNull(str, ObjectsNotNullTest::createMessage));

        assertEquals("a demo string", exception.getMessage());
    }

    private static String createMessage() {
        return "a demo string";
    }

}
