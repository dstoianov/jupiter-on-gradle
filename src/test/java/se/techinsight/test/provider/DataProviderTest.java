package se.techinsight.test.provider;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class DataProviderTest {

    @Tag("data")
    @DisplayName("Test data provider in Other class, like in TestNG")
    @ParameterizedTest(name = "#{index} value ''{0}'', expected result ''{1}''")
    @MethodSource("se.techinsight.test.provider.DataProvider#provideStringsForIsBlank")
    void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input, boolean expected) {
        assertEquals(expected, StringUtils.isBlank(input));
    }


    @ParameterizedTest(name = "#{index} value under test \"{0}\"")
    @MethodSource("se.techinsight.test.provider.DataProvider#blankStrings")
    void isBlank_ShouldReturnTrueForNullOrBlankStringsExternalSource(String input) {
        assertTrue(StringUtils.isBlank(input));
    }

}
