package se.techinsight.test.feature.extension;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

import java.lang.reflect.Field;

public class ServiceResolverExtension implements TestInstancePostProcessor {

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) {

        //https://youtu.be/DAszLeWMsqU?t=887

        Field[] fields = testInstance.getClass().getDeclaredFields();
        for (Field field : fields) {
//            if (field.isAnnotationPresent(Inject.class) &&
//                field.getType()) {

//            }
        }
    }
}
