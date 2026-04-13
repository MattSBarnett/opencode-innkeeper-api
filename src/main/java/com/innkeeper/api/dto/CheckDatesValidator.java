package com.innkeeper.api.dto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class CheckDatesValidator implements ConstraintValidator<CheckDatesValid, BookingDTO> {

    @Override
    public void initialize(CheckDatesValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(BookingDTO bookingDTO, ConstraintValidatorContext context) {
        if (bookingDTO == null || bookingDTO.getCheckInDate() == null || bookingDTO.getCheckOutDate() == null) {
            return true; // Let other annotations handle nulls
        }
        return !bookingDTO.getCheckOutDate().isBefore(bookingDTO.getCheckInDate());
    }
}
