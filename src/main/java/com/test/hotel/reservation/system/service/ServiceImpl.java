package com.test.hotel.reservation.system.service;

import com.test.hotel.reservation.system.entities.Room;
import com.test.hotel.reservation.system.entities.RoomType;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ServiceImpl implements Service{

    private final Map<Integer, Room> rooms = new HashMap<>();
    @Override
    public void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight) {
        if (roomNumber <= 0) throw new IllegalArgumentException("Room number must be > 0");
        if (roomPricePerNight <= 0) throw new IllegalArgumentException("RoomPricePerNight must be > 0");
        if(rooms.containsKey(roomNumber)) {
            return;
        }
        rooms.put(roomNumber, new Room(roomNumber, roomType, roomPricePerNight));
    }

    @Override
    public void bookRoom(int userId, int roomNumber, LocalDate checkIn, LocalDate checkOut) {

    }

    @Override
    public void printAll() {

    }

    @Override
    public void setUser(int userId, int balance) {

    }

    @Override
    public void printAllUsers() {

    }

    public Map<Integer, Room> getRooms() {
        return rooms;
    }
}
