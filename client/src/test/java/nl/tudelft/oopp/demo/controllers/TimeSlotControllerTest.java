package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import nl.tudelft.oopp.demo.models.ReservationRequestModel;
import org.junit.jupiter.api.Test;

public class TimeSlotControllerTest {
    private ReservationRequestModel testReservationRequestModel = new ReservationRequestModel(
            123L,
            "testDate",
            "testTimeslots",
            "testUser"
    );

    @Test
    public void getAvailableTimeslotsTest() {
        assertNull(TimeSlotsController.getAvailableTimeSlots("testRoom", "testDate"),
                "Expected null, with invalid http response");
    }

    @Test
    public void createReservationTest() {
        assertFalse(TimeSlotsController.createReservation(testReservationRequestModel),
                "Expected null, with invalid http response");
    }
}
