package compare

import org.junit.jupiter.api.Test

class BigDecimalTest {

    @Test
    void compareTest1() {
        BigDecimal a = new BigDecimal("1.000")
        BigDecimal b = new BigDecimal("1")

        assert a == b
    }

    /*
    assert new BigDecimal("1.0001") == new BigDecimal("1")
       |                        |  |
       1.0001                   |  1
                                false
     */

    @Test
    void compareTest2() {
        try {
            assert new BigDecimal("1.0001") == new BigDecimal("1")
        } catch (AssertionError e) {
            assert e.message.contains("false")
        }
    }


}
