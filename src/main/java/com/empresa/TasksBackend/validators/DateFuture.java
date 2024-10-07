package com.empresa.TasksBackend.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateFutureValidator.class)
public @interface DateFuture {
    String message() default "A data deve ser maior que a atual";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
