package se.techinsight.test.feature.parameter;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import se.techinsight.domain.Person;
import se.techinsight.domain.User;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({UserParameterResolver.class, PersonParameterResolver.class})
class DemoParameterResolverTest {

    @Test
    void testUser(User user) {
        assertAll(
                () -> assertEquals("aaa", user.getFirstName()),
                () -> assertEquals("bbb", user.getLastName()),
                () -> assertEquals(23, user.getAge())
        );
    }

    @Test
    void testPerson(Person person) {
        assertAll(
                () -> assertEquals("bbb", person.getFirstName()),
                () -> assertEquals("ccc", person.getLastName()),
                () -> assertEquals(36, person.getAge())
        );
    }

    @Test
    void testUserAndPerson(User user, Person person) {
        assertAll(
                () -> assertEquals("aaa", user.getFirstName()),
                () -> assertEquals("bbb", user.getLastName()),
                () -> assertEquals(23, user.getAge()),

                () -> assertEquals("bbb", person.getFirstName()),
                () -> assertEquals("ccc", person.getLastName()),
                () -> assertEquals(36, person.getAge())
        );
    }


    static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of("AAA"),
                Arguments.of("BBB"),
                Arguments.of("CCC")
        );
    }


    @ParameterizedTest(name = "#{index} value under test \"{0}\", Person {1}")
    @MethodSource("data")
    void testDataProviderAndParameterResolver(String input, Person person) {
        assertTrue(StringUtils.isNotBlank(input));

        assertAll(
                () -> assertEquals("bbb", person.getFirstName()),
                () -> assertEquals("ccc", person.getLastName()),
                () -> assertEquals(36, person.getAge())
        );
    }

}
