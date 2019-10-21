package se.techinsight.test.feature.parameter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import se.techinsight.domain.User;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(UserParameterResolver.class)
class DiInUserTest {

    @Test
    void name(User user) {

        assertAll(
            () -> assertEquals("aaa", user.getFirstName()),
            () -> assertEquals("bbb", user.getLastName()),
            () -> assertEquals(23, user.getAge())
        );
    }
}
