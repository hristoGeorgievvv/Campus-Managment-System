package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;


public class BikeReservationControllerTest {

    @Test
    public void makeOrDeleteReseravtionTest() {
        assertFalse(BikeReservationController.makeOrDeleteReservation("testBuilding"),
                "Expected null, with invalid http response");
    }

    @Test
    public void userHasBikeTest() {
        assertFalse(BikeReservationController.userHasBike(),
                "Expected null, with invalid http response");
    }
}
