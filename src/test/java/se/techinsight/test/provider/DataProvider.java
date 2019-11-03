package se.techinsight.test.provider;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;


public class DataProvider {

    private static Stream<Arguments> provideStringsForIsBlank() {
        return Stream.of(
                Arguments.of(null, true),
                Arguments.of("", true),
                Arguments.of("  ", true),
                Arguments.of("not blank", false)
        );
    }

    static Stream<String> blankStrings() {
        return Stream.of(null, "", "  ");
    }


}
