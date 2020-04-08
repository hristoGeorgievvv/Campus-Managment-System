package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;



public class RoomReservationControllerTest {
    @Test
    public void getReservationsTest() {
        assertNull(RoomReservationController.getReservations(),
                "Expected null, with invalid http response");
    }

    @Test
    public void getClassroomsTest() {
        assertEquals(RoomReservationController.getReservationsPerUser().size(), 0,
                "Expected null, with invalid http response");
    }
}