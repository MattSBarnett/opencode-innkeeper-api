package com.innkeeper.api.service;

import com.innkeeper.api.dto.BookingDTO;
import com.innkeeper.api.entity.Booking;
import com.innkeeper.api.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void createBooking_returnsSavedDTO() {
        BookingDTO dto = new BookingDTO();
        dto.setGuestName("John Doe");
        dto.setRoomNumber("101");
        dto.setCheckInDate(LocalDate.now());
        dto.setCheckOutDate(LocalDate.now().plusDays(1));

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
