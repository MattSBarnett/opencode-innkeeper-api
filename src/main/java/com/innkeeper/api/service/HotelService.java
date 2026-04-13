package com.innkeeper.api.service;

import com.innkeeper.api.dto.HotelDTO;
import com.innkeeper.api.dto.RoomDTO;
import com.innkeeper.api.entity.Hotel;
import com.innkeeper.api.entity.Room;
import com.innkeeper.api.repository.HotelRepository;
import com.innkeeper.api.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    public HotelService(HotelRepository hotelRepository, RoomRepository roomRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

    public List<HotelDTO> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(this::convertToHotelDTO)
                .collect(Collectors.toList());
    }

    public List<RoomDTO> getRoomsByHotel(Long hotelId) {
        return roomRepository.findByHotelId(hotelId).stream()
                .map(this::convertToRoomDTO)
                .collect(Collectors.toList());
    }

    public List<RoomDTO> getAvailableRooms(Long hotelId, LocalDate checkIn, LocalDate checkOut) {
        return roomRepository.findAvailableRooms(hotelId, checkIn, checkOut).stream()
                .map(this::convertToRoomDTO)
                .collect(Collectors.toList());
    }

    private HotelDTO convertToHotelDTO(Hotel hotel) {
        return new HotelDTO(hotel.getId(), hotel.getName(), hotel.getAddress());
    }

    private RoomDTO convertToRoomDTO(Room room) {
        return new RoomDTO(room.getId(), room.getRoomNumber(), room.getHotel().getId());
    }
}
