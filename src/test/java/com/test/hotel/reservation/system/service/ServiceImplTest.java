package com.test.hotel.reservation.system.service;

import com.test.hotel.reservation.system.entities.RoomType;
import com.test.hotel.reservation.system.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceImplTest {
    private ServiceImpl service;

    @BeforeEach
    void setup() {
        service = new ServiceImpl();

        // Rooms
        service.setRoom(1, RoomType.STANDARD, 1000);
        service.setRoom(2, RoomType.JUNIOR, 2000);
        service.setRoom(3, RoomType.MASTER, 3000);

        // Users
        service.setUser(1, 5000);
        service.setUser(2, 10000);
    }

    @Test
    public void shouldTestSetRoom(){
        Assertions.assertThat(service.rooms.size()).isEqualTo(3);
        Assertions.assertThat(service.rooms.get(1).pricePerNight()).isEqualTo(1000);
    }

    @Test
    public void shouldTestSetUser(){
        Assertions.assertThat(service.users.size()).isEqualTo(2);
        Assertions.assertThat(service.users.get(1).balance()).isEqualTo(5000);
    }

    @Test
    void shouldBookRoomSuccessfully_whenBalanceAndAvailabilityAreOk() {
        service.bookRoom(
                1,
                1,
                LocalDate.of(2026, 7, 7),
                LocalDate.of(2026, 7, 8) // 1 night
        );

        User updatedUser = service.users.get(1);
        Assertions.assertThat(updatedUser.balance()).isEqualTo(4000);
        Assertions.assertThat(service.bookings.size()).isEqualTo(1);
    }

    @Test
    void shouldFail_whenUserDoesNotExist() {
        assertThrows(IllegalArgumentException.class, () ->
                service.bookRoom(
                        5,
                        1,
                        LocalDate.of(2026, 7, 7),
                        LocalDate.of(2026, 7, 8)
                )
        );
    }

    @Test
    void shouldFail_whenRoomDoesNotExist() {
        assertThrows(IllegalArgumentException.class, () ->
                service.bookRoom(
                        1,
                        5,
                        LocalDate.of(2026, 7, 7),
                        LocalDate.of(2026, 7, 8)
                )
        );
    }

    @Test
    void shouldFail_whenDatesAreInvalid() {
        assertThrows(IllegalArgumentException.class, () ->
                service.bookRoom(
                        1,
                        1,
                        LocalDate.of(2026, 7, 8),
                        LocalDate.of(2026, 7, 7)
                )
        );
    }

    @Test
    void shouldFail_whenInsufficientBalance() {
        assertThrows(IllegalStateException.class, () ->
                service.bookRoom(
                        1,
                        2,
                        LocalDate.of(2026, 6, 30),
                        LocalDate.of(2026, 7, 5)
                )
        );

        Assertions.assertThat(service.bookings.size()).isEqualTo(0);
    }
}
