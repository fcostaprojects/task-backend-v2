package com.empresa.TasksBackend.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateFutureValidator implements ConstraintValidator<DateFuture, LocalDate> {

    private boolean isEqualOrFutureDate(LocalDate date) {
        return date.isEqual(LocalDate.now()) || date.isAfter(LocalDate.now());
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        return isEqualOrFutureDate(date);
    }
}
