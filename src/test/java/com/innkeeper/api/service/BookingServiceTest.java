package com.innkeeper.api.service;

import com.innkeeper.api.dto.BookingDTO;
import com.innkeeper.api.entity.Booking;
import com.innkeeper.api.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BookingServiceTest {

    private BookingService bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookingService = new BookingService(bookingRepository);
    }

    @Test
    void getAllBookings_returnsEmptyList() {
        when(bookingRepository.findAll()).thenReturn(Collections.emptyList());
        List<BookingDTO> result = bookingService.getAllBookings();
        assertEquals(0, result.size());
    }

    @Test
    void getAllBookings_returnsBookingsList() {
        Booking booking = new Booking();
        booking.setId(1L);
        booking.setGuestName("John Doe");
        booking.setRoomNumber("101");
        booking.setCheckInDate(LocalDate.now());
        booking.setCheckOutDate(LocalDate.now().plusDays(1));

        when(bookingRepository.findAll()).thenReturn(Arrays.asList(booking));
        List<BookingDTO> result = bookingService.getAllBookings();

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getGuestName());
    }

    @Test
    void getBookingById_existingBooking_returnsDTO() {
        Booking booking = new Booking();
        booking.setId(1L);
        booking.setGuestName("John Doe");
        booking.setRoomNumber("101");
        booking.setCheckInDate(LocalDate.now());
        booking.setCheckOutDate(LocalDate.now().plusDays(1));

        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));
        BookingDTO result = bookingService.getBookingById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getGuestName());
    }

    @Test
    void getBookingById_nonExistingBooking_returnsNull() {
        when(bookingRepository.findById(999L)).thenReturn(Optional.empty());
        BookingDTO result = bookingService.getBookingById(999L);

        assertNull(result);
    }

    @Test
    void createBooking_returnsSavedDTO() {
        BookingDTO dto = new BookingDTO();
        dto.setGuestName("John Doe");
        dto.setRoomNumber("101");
        dto.setCheckInDate(LocalDate.now().plusDays(1));
        dto.setCheckOutDate(LocalDate.now().plusDays(3));

        Booking savedBooking = new Booking();
        savedBooking.setId(1L);
        savedBooking.setGuestName(dto.getGuestName());
        savedBooking.setRoomNumber(dto.getRoomNumber());
        savedBooking.setCheckInDate(dto.getCheckInDate());
        savedBooking.setCheckOutDate(dto.getCheckOutDate());

        when(bookingRepository.save(any(Booking.class))).thenReturn(savedBooking);

        BookingDTO result = bookingService.createBooking(dto);

        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getGuestName());
    }
}
