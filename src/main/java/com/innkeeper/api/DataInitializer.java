package com.innkeeper.api;

import com.innkeeper.api.entity.Hotel;
import com.innkeeper.api.entity.Room;
import com.innkeeper.api.repository.HotelRepository;
import com.innkeeper.api.repository.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    public DataInitializer(HotelRepository hotelRepository, RoomRepository roomRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public void run(String... args) {
        if (hotelRepository.count() == 0) {
            Hotel grandHotel = new Hotel("Grand Hotel", "123 Main Street, Downtown");
            grandHotel.addRoom(new Room("101"));
            grandHotel.addRoom(new Room("102"));
            grandHotel.addRoom(new Room("103"));
            hotelRepository.save(grandHotel);

            Hotel seasideInn = new Hotel("Seaside Inn", "456 Beach Road, Coastal Town");
            seasideInn.addRoom(new Room("201"));
            seasideInn.addRoom(new Room("202"));
            seasideInn.addRoom(new Room("203"));
            hotelRepository.save(seasideInn);
        }
    }
}
