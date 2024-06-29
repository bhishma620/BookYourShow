package com.bhishma.bookyourshow.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DurationFormatValidator implements ConstraintValidator<ValidDurationFormat, String> {

    @Override
    public void initialize(ValidDurationFormat constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            return false;
        }

        String regex = "^([0-9]?[0-9][0-9]:[0-5][0-9]:[0-5][0-9])$";
        return value.matches(regex);
    }
}
