package se.techinsight.test.feature.extension;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import se.techinsight.domain.User;
import se.techinsight.jupiter.annotation.Inject;

@ExtendWith(ServiceResolverExtension.class)
class SomeTest {

    @Inject
    User user;

    @Test
    void name() {

    }
}
