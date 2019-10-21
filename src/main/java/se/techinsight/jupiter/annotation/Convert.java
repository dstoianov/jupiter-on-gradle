package se.techinsight.jupiter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
//https://github.com/dtuchs/QAMeetupDemo/blob/master/src/main/java/com/propellerads/jupiter/converter/UserParameterConverter.java
//@ConvertWith(UserParameterConverter.class)
public @interface Convert {
}
