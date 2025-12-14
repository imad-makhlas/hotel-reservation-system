package com.test.hotel.reservation.system.service;

import com.test.hotel.reservation.system.entities.RoomType;

import java.time.LocalDate;

public interface Service {
    void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight);
    void bookRoom(int userId, int roomNumber, LocalDate checkIn, LocalDate checkOut);
    void printAll();
    void setUser(int userId, int balance);
    void printAllUsers();
}
