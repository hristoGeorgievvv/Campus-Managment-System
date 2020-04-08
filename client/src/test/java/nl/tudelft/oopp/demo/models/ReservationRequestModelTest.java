package nl.tudelft.oopp.demo.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;


class ReservationRequestModelTest {

    private ReservationRequestModel resmodelequality = new ReservationRequestModel(
            (long)1,
            "testDate",
            "testTimeSlot",
            "testUserName"
    );

    private ReservationRequestModel resmodel = new ReservationRequestModel(
            (long)1,
            "testDate",
            "testTimeSlot",
            "testUserName"
    );

    private ReservationRequestModel resmodel2 = new ReservationRequestModel(
            (long)2,
            "testDate2",
            "testTimeSlot2",
            "testUserName2"
    );

    @Test
    void getDate() {
        assertNotEquals(resmodel.getDate(), resmodel2.getDate(),
                "Different dates shouldn't be equal");
        assertEquals("testDate", resmodel.getDate(),
                "getDate failed to produce correct value");
        assertEquals("testDate2", resmodel2.getDate(),
                "getDate failed to produce correct value");
    }

    @Test
    void getTimeSlot() {
        assertNotEquals(resmodel.getTimeSlot(), resmodel2.getTimeSlot(),
                "Different timeslots shouldnt be equal");
        assertEquals("testTimeSlot", resmodel.getTimeSlot(),
                "getTimeSlot failed to produce correct value");
        assertEquals("testTimeSlot2", resmodel2.getTimeSlot(),
                "getTimeSlot failed to produce correct value");
    }

    @Test
    void getUserName() {
        assertNotEquals(resmodel.getUserName(), resmodel2.getUserName(),
                "Different usernames shouldnt be equal");
        assertEquals("testUserName", resmodel.getUserName(),
                "getUserName failed to produce correct value");
        assertEquals("testUserName2", resmodel2.getUserName(),
                "getUserName failed to produce correct value");
    }

    @Test
    void getRoomId() {
        assertNotEquals(resmodel.getRoomId(), resmodel2.getRoomId(),
                "Different ids shouldnt be equal");
        assertEquals(1, resmodel.getRoomId(),
                "getRoomId failed to produce correct value");
        assertEquals(2, resmodel2.getRoomId(),
                "getRoomId failed to produce correct value");
    }

    @Test
    void equalsSame() {
        assertEquals(resmodel,resmodel,
                "Same object should return true in equals");
    }

    @Test
    void notEquals() {
        assertNotEquals(resmodel,resmodel2,
                "Different objects should return false in equals");
    }

    @Test
    void equalsDifferent() {
        assertEquals(resmodel,resmodelequality,
                "Objects with same values should be equal");
    }
}