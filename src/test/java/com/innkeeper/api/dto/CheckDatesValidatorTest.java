package com.innkeeper.api.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CheckDatesValidatorTest {

    private CheckDatesValidator validator;

    @BeforeEach
    void setUp() {
        validator = new CheckDatesValidator();
    }

    @Test
    void isValid_checkoutAfterCheckin_returnsTrue() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setCheckInDate(LocalDate.now().plusDays(1));
        bookingDTO.setCheckOutDate(LocalDate.now().plusDays(3));

        assertTrue(validator.isValid(bookingDTO, null));
    }

    @Test
    void isValid_checkoutSameAsCheckin_returnsTrue() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setCheckInDate(LocalDate.now().plusDays(1));
        bookingDTO.setCheckOutDate(LocalDate.now().plusDays(1));

        assertTrue(validator.isValid(bookingDTO, null));
    }

    @Test
    void isValid_checkoutBeforeCheckin_returnsFalse() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setCheckInDate(LocalDate.now().plusDays(3));
        bookingDTO.setCheckOutDate(LocalDate.now().plusDays(1));

        assertFalse(validator.isValid(bookingDTO, null));
    }

    @Test
    void isValid_nullDates_returnsTrue() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setCheckInDate(null);
        bookingDTO.setCheckOutDate(null);

        assertTrue(validator.isValid(bookingDTO, null));
    }

    @Test
    void isValid_nullCheckInDate_returnsTrue() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setCheckInDate(null);
        bookingDTO.setCheckOutDate(LocalDate.now().plusDays(1));

        assertTrue(validator.isValid(bookingDTO, null));
    }

    @Test
    void isValid_nullCheckoutDate_returnsTrue() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setCheckInDate(LocalDate.now().plusDays(1));
        bookingDTO.setCheckOutDate(null);

        assertTrue(validator.isValid(bookingDTO, null));
    }
}
