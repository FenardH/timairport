package be.technifutur.java.timairport.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class Not0Validator implements ConstraintValidator<Not0, Number> {
    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        if(!value.equals(0L)) {
            return true;
        } else {
            context.buildConstraintViolationWithTemplate("value should not be 0").addConstraintViolation();
            return false;
        }
    }
}
