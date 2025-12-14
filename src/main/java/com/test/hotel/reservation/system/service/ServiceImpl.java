package com.test.hotel.reservation.system.service;

import com.test.hotel.reservation.system.entities.Booking;
import com.test.hotel.reservation.system.entities.Room;
import com.test.hotel.reservation.system.entities.RoomType;
import com.test.hotel.reservation.system.entities.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceImpl implements Service{

    final Map<Integer, Room> rooms = new HashMap<>();
    final Map<Integer, User> users = new HashMap<>();
    final List<Booking> bookings = new ArrayList<>();

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
        if (checkIn == null || checkOut == null) {
            throw new IllegalArgumentException("checkIn/checkOut must not be null");
        }
        if (!checkOut.isAfter(checkIn)) {
            throw new IllegalArgumentException("checkOut must be after checkIn");
        }
        User user = users.get(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + userId);
        }
        Room room = rooms.get(roomNumber);
        if (room == null) {
            throw new IllegalArgumentException("Room not found: " + roomNumber);
        }
        for (Booking b : bookings) {
            if (b.roomNumber() != roomNumber) continue;
            boolean overlap = checkIn.isBefore(b.endDate()) && b.startDate().isBefore(checkOut);
            if (overlap) {
                throw new IllegalStateException("Room " + roomNumber + " is not free for that period");
            }
        }

        int nights = (int) ChronoUnit.DAYS.between(checkIn, checkOut);
        int total = nights * room.pricePerNight();

        if (user.balance() < total) {
            throw new IllegalStateException("Insufficient balance");
        }
        users.put(userId, new User(user.userId(), user.balance() - total));
        bookings.add(new Booking( room.roomNumber(), room.roomType(), room.pricePerNight(), user.userId(), checkIn, checkOut ));
    }

    @Override
    public void printAll() {

    }

    @Override
    public void setUser(int userId, int balance) {
        if (userId <= 0 ) throw new IllegalArgumentException("userId must be > 0");
        if (balance <= 0 ) throw new IllegalArgumentException("balance must be > 0");
        if(users.containsKey(userId)) {
            return;
        }
        users.put(userId, new User(userId, balance));

    }

    @Override
    public void printAllUsers() {

    }
}
