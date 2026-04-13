package com.innkeeper.api.service;

import com.innkeeper.api.dto.BookingDTO;
import com.innkeeper.api.entity.Booking;
import com.innkeeper.api.entity.Hotel;
import com.innkeeper.api.entity.Room;
import com.innkeeper.api.repository.BookingRepository;
import com.innkeeper.api.repository.HotelRepository;
import com.innkeeper.api.repository.RoomRepository;
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

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private RoomRepository roomRepository;

    private Hotel testHotel;
    private Room testRoom;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookingService = new BookingService(bookingRepository, hotelRepository, roomRepository);
        
        testHotel = new Hotel("Test Hotel", "123 Test St");
        testHotel.setId(1L);
        
        testRoom = new Room("101");
        testRoom.setId(1L);
        testRoom.setHotel(testHotel);
    }

    @Test
    void getAllBookings_returnsEmptyList() {
        when(bookingRepository.findAll()).thenReturn(Collections.emptyList());
        List<BookingDTO> result = bookingService.getAllBookings();
        assertEquals(0, result.size());
    }

    @Test
    void getAllBookings_returnsBookingsList() {
        Booking booking = createTestBooking();
        
        when(bookingRepository.findAll()).thenReturn(Arrays.asList(booking));
        List<BookingDTO> result = bookingService.getAllBookings();

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getGuestName());
    }

    @Test
    void getBookingById_existingBooking_returnsDTO() {
        Booking booking = createTestBooking();
        
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
        dto.setHotelId(1L);
        dto.setRoomId(1L);
        dto.setCheckInDate(LocalDate.now().plusDays(1));
        dto.setCheckOutDate(LocalDate.now().plusDays(3));

        Booking savedBooking = createTestBooking();
        
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(testHotel));
        when(roomRepository.findById(1L)).thenReturn(Optional.of(testRoom));
        when(bookingRepository.save(any(Booking.class))).thenReturn(savedBooking);

        BookingDTO result = bookingService.createBooking(dto);

        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getGuestName());
    }

    private Booking createTestBooking() {
        Booking booking = new Booking();
        booking.setId(1L);
        booking.setGuestName("John Doe");
        booking.setHotel(testHotel);
        booking.setRoom(testRoom);
        booking.setCheckInDate(LocalDate.now().plusDays(1));
        booking.setCheckOutDate(LocalDate.now().plusDays(3));
        return booking;
    }
}
