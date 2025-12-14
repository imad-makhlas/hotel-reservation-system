package com.test.hotel.reservation.system.entities;

public record Room(
        int roomNumber,
        RoomType roomType,
        int pricePerNight
) {
}
