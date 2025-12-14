package com.test.hotel.reservation.system.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record Booking(
        Room room,
        User user,
        LocalDate startDate,
        LocalDate endDate
) {
    public int periodInDays(){
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }

    public int total(){
        return periodInDays()* room().pricePerNight();
    }
}
