package com.test.hotel.reservation.system.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record Booking(
        int roomNumber,
        RoomType roomType,
        int pricePerNight,
        int userId,
        LocalDate startDate,
        LocalDate endDate
) {
}
