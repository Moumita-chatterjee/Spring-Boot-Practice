package com.ltp.gradesubmission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


//@target- what this annotation bind to is it method, field, constructor 
@Target(ElementType.FIELD)
//we want to retain during the run time
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ScoreValidator.class)
public @interface  Score {

    //in spring boot when defining constraint we have to always define the groups() and payloads[]
    String message() default "Invalid Data";
    Class<?>[] groups() default {};
    Class <? extends Payload> [] payload() default {};
}
