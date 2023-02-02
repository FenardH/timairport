package be.technifutur.java.timairport.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class InPastValidator implements ConstraintValidator<InPast, LocalDate> {
    private InPast constraint;
    @Override
    public void initialize(InPast constraintAnnotation) {
        this.constraint = constraintAnnotation;
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        if(date.isAfter(LocalDate.now().minus(this.constraint.value(), this.constraint.unit()))) {
            context.buildConstraintViolationWithTemplate(String.format("registration time should be at least %s %s before!!!",
                    constraint.value(),
                    constraint.unit().toString().toLowerCase())).addConstraintViolation();
            return false;
        }
        return true;
    }
}
