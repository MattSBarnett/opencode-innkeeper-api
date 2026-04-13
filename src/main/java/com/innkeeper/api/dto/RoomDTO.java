package com.innkeeper.api.dto;

public class RoomDTO {
    private Long id;
    private String roomNumber;
    private Long hotelId;

    public RoomDTO() {
    }

    public RoomDTO(Long id, String roomNumber, Long hotelId) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.hotelId = hotelId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }
}
