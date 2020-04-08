package nl.tudelft.oopp.demo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import nl.tudelft.oopp.demo.repositories.RoomReservationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class RoomReservationTest {

    @Autowired
    private RoomReservationRepository roomRep;

    private Building building = new Building(
            "testHours",
            "testName",
            "testDepartment",
            "testLocation",
            5
    );
    private ClassRoom classroom = new ClassRoom(
            "testName",
            building,
            5
    );
    private ClassRoom classroom2 = new ClassRoom(
            "testName2",
            building,
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
    private TimeSlots timeslot = new TimeSlots("testDuration");
    private TimeSlots timeslot2 = new TimeSlots("testDuration2");
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

    /**
     * Testing.
     */

    @Test
    public void roomReservationConstructorTestEmpty() {
        RoomReservation roomReservation = new RoomReservation();
        assertNotNull(roomReservation, "Expected not null");
    }

    @Test
    public void roomReservationConstructorTest() {
        assertNotNull(roomReservation, "Expected not null");
        assertEquals("testDate", roomReservation.getDateOfReservation(),
                "Not properly saved Date");
        assertEquals(user, roomReservation.getUser(), "Not properly saved user");
        assertEquals(classroom, roomReservation.getClassRoom(), "Not properly saved classroom");
        assertEquals(timeslot, roomReservation.getTimeSlots(), "Not properly saved timeslot");
    }

    @Test
    public void getIdTest() {
        roomRep.save(roomReservation);
        assertNotNull(roomReservation.getId(), "Id not generated");
    }

    @Test
    public void setIdTest() {
        roomRep.save(roomReservation);
        roomReservation.setId(123L);
        assertEquals(123L, roomReservation.getId(), "setID is not functioning");
    }

    @Test
    public void getDateOfReservationTest() {
        assertEquals("testDate", roomReservation.getDateOfReservation(),
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
