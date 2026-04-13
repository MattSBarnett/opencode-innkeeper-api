package com.innkeeper.api.dto;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = CheckDatesValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckDatesValid {
    String message() default "Check-out date must be after or the same as check-in date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
