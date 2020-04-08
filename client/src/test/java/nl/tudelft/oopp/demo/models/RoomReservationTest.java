package nl.tudelft.oopp.demo.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import nl.tudelft.oopp.demo.models.ClassRoom;
import nl.tudelft.oopp.demo.models.RoomReservation;
import nl.tudelft.oopp.demo.models.TimeSlots;
import nl.tudelft.oopp.demo.models.User;

import org.junit.jupiter.api.Test;


public class RoomReservationTest {
    private ClassRoom classroom = new ClassRoom(
            123L,
            "testName",
            5
    );
    private ClassRoom classroom2 = new ClassRoom(
            321L,
            "testName",
            7
    );
    private User user = new User(
            "testName",
            "testType",
            "testEmail",
            "testPword",
            false
    );
    private User user2 = new User(
            "testName2",
            "testType2",
            "testEmail2",
            "testPword2",
            false
    );
    private TimeSlots timeslot = new TimeSlots(123L, "testDuration");
    private TimeSlots timeslot2 = new TimeSlots(321L, "testDuration2");
    private RoomReservation roomReservation = new RoomReservation(
            "testDate",
            user,
            classroom,
            timeslot
    );
    private RoomReservation roomReservation2 = new RoomReservation(
            "testDate",
            user,
            classroom,
            timeslot
    );


    /**.
     * Testing
     */
    @Test
    public void roomReservationConstructorTestEmpty() {
        RoomReservation roomReservation = new RoomReservation();
        assertNotNull(roomReservation, "Expected not null");
    }

    @Test
    public void roomReservationConstructorTest() {
        assertNotNull(roomReservation, "Expected not null");
        assertEquals("testDate", roomReservation.getDate(),
                "Not properly saved Date");
        assertEquals(user, roomReservation.getUser(), "Not properly saved user");
        assertEquals(classroom, roomReservation.getClassRoom(), "Not properly saved classroom");
        assertEquals(timeslot, roomReservation.getTimeSlots(), "Not properly saved timeslot");
    }

    @Test
    public void getDateTest() {
        assertEquals("testDate", roomReservation.getDate(),
                "getDateOfReservation incorrect");
    }

    @Test
    public void getUserTest() {
        assertEquals(user, roomReservation.getUser(), "getUser incorrect");
    }

    @Test
    public void getClassroomTest() {
        assertEquals(classroom, roomReservation.getClassRoom(), "getClassroom incorrect");
    }

    @Test
    public void getTimeSlotsTest() {
        assertEquals(timeslot, roomReservation.getTimeSlots(), "getTimeSlots incorrect");
    }

    @Test
    public void setDateTest() {
        roomReservation.setDate("NewDate");
        assertEquals("NewDate", roomReservation.getDate(),
                "setDate incorrect, did not set correctly");
    }

    //@Test
    //   public void getNumberOfPeopleTest() {
    //       assertNotNull(roomReservation.getNumberOfPeople(),
    //       "Number of people should not be null");
    //   }

    /**
     * equals testing.
     */

    @Test
    public void equalsTest() {
        assertTrue(roomReservation.equals(roomReservation2), "equal reservations should be equal");
        assertEquals(roomReservation, roomReservation2, "equal reservations should be equal");
    }

    @Test
    public void equalsTestSame() {
        assertSame(roomReservation, roomReservation, "same reservation should be same");
        assertTrue(roomReservation.equals(roomReservation), "same reservation should be equal");
    }

    @Test
    public void equalsTestNotEqual() {
        RoomReservation roomReservation2 = new RoomReservation(
                "testDate2",
                user2,
                classroom2,
                timeslot2
        );
        assertFalse(roomReservation.equals(roomReservation2),
                "not equal reservations should not be equal");
        assertNotEquals(roomReservation, roomReservation2,
                "not equal reservations should not be equal");
    }

    @Test
    public void equalsTestAttributesDateOfReservation() {
        RoomReservation roomReservation2 = new RoomReservation(
                "testDate2",
                user,
                classroom,
                timeslot
        );
        assertFalse(roomReservation.equals(roomReservation2),
                "not equal reservations should not be equal, Date is not equal");
        assertNotEquals(roomReservation, roomReservation2,
                "not equal reservations should not be equal, Date is not equal");
    }

    @Test
    public void equalsTestAttributesUser() {
        RoomReservation roomReservation2 = new RoomReservation(
                "testDate",
                user2,
                classroom,
                timeslot
        );
        assertFalse(roomReservation.equals(roomReservation2),
                "not equal reservations should not be equal, user is not equal");
        assertNotEquals(roomReservation, roomReservation2,
                "not equal reservations should not be equal, user is not equal");
    }

    @Test
    public void equalsTestAttributesClassroom() {
        RoomReservation roomReservation2 = new RoomReservation(
                "testDate",
                user,
                classroom2,
                timeslot
        );
        assertFalse(roomReservation.equals(roomReservation2),
                "not equal reservations should not be equal, classroom is not equal");
        assertNotEquals(roomReservation, roomReservation2,
                "not equal reservations should not be equal, classroom is not equal");
    }

    @Test
    public void equalsTestAttributesTimeSlot() {
        RoomReservation roomReservation2 = new RoomReservation(
                "testDate",
                user,
                classroom,
                timeslot2
        );
        assertFalse(roomReservation.equals(roomReservation2),
                "not equal reservations should not be equal, TimeSlot is not equal");
        assertNotEquals(roomReservation, roomReservation2,
                "not equal reservations should not be equal, TimeSlot is not equal");
    }

}
