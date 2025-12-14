package com.test.hotel.reservation.system.service;

import com.test.hotel.reservation.system.entities.RoomType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServiceImplTest {
    private ServiceImpl service;
    @BeforeEach
    void setUp(){
        service = new ServiceImpl();
    }

    @Test
    public void shouldTestSetRoom(){
        service.setRoom(1, RoomType.STANDARD, 1000);
        service.setRoom(2, RoomType.JUNIOR, 2000);
        service.setRoom(1, RoomType.MASTER, 3000);
        Assertions.assertThat(service.getRooms().size()).isEqualTo(2);
        Assertions.assertThat(service.getRooms().get(1).pricePerNight()).isEqualTo(1000);
    }

    @Test
    public void shouldTestSetUser(){
        service.setUser(1, 5000);
        service.setUser(2, 10000);
        Assertions.assertThat(service.getUsers().size()).isEqualTo(2);
        Assertions.assertThat(service.getUsers().get(1).balance()).isEqualTo(5000);
    }
}
