package com.bhishma.bookyourshow.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DurationFormatValidator.class)
public @interface ValidDurationFormat {

    String message() default "Invalid duration format. Must be in HH:MM:SS format.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
