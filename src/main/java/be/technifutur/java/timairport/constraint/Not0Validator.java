package be.technifutur.java.timairport.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class Not0Validator implements ConstraintValidator<Not0, Number> {
    @Override
    public boolean isValid(Number number, ConstraintValidatorContext constraintValidatorContext) {
        return !number.equals(0);
    }
}
