package com.test.hotel.reservation.system.entities;

import java.time.LocalDate;

public record Booking(
        Room room,
        User user,
        LocalDate startDate,
        LocalDate endDate
) {
}
