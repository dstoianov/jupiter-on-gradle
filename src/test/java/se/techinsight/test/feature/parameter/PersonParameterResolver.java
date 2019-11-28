package se.techinsight.test.feature.parameter;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import se.techinsight.domain.Person;

public class PersonParameterResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return parameterContext.getParameter().getType().equals(Person.class);
    }

    @Override
    public Person resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return new Person("bbb", "ccc", 36);
    }
}
