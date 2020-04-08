package nl.tudelft.oopp.demo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import nl.tudelft.oopp.demo.repositories.ClassRoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ClassroomTest {

    @Autowired
    private ClassRoomRepository classRep;

    private Building building = new Building(
            "testHours",
            "testName",
            "testDepartment",
            "testLocation",
            5
    );
    private Building building2 = new Building(
            "testHours2",
            "testName2",
            "testDepartment2",
            "testLocation2",
            10
    );
    private ClassRoom classroom = new ClassRoom(
            "testName",
            building,
            5
    );
    private ClassRoom classroom2 = new ClassRoom(
            "testName",
            building,
            5
    );
    private User user = new User(
            "testName",
            "testType",
            "testEmail",
            "testPword",
            true
    );
    private TimeSlots timeslot = new TimeSlots("testDuration");
    private RoomReservation roomReservation = new RoomReservation(
            "testDate",
            user,
            classroom,
            timeslot
    );

    /**
     * testing.
     */

    @Test
    public void classroomContsructorTestEmpty() {
        ClassRoom classRoom = new ClassRoom();
        assertNotNull(classroom, "Expected not null");
    }

    @Test
    public void classroomContsructorTest() {
        assertNotNull(classroom, "Expected not null");
        assertEquals("testName", classroom.getRoomName(), "roomName not saved properly");
        assertEquals(building, classroom.getBuilding(), "building not saved properly");
        assertEquals(5, classroom.getNumberOfPeople(), "numberOfPeople not saved properly");
    }

    @Test
    public void getIdTest() {
        classRep.save(classroom);
        assertNotNull(classroom.getId(), "Expected an id");
    }

    @Test
    public void setIdTest() {
        classRep.save(classroom);
        classroom.setId(123L);
        assertEquals(123L, classroom.getId(), "setID is not functioning");
    }

    @Test
    public void getRoomNameTest() {
        assertEquals("testName", classroom.getRoomName(), "roomName not saved properly");
    }

    @Test
    public void getBuildingTest() {
        assertEquals(building, classroom.getBuilding(), "building not saved properly");
    }

    @Test
    public void getNumberOfPeopleTest() {
        assertEquals(5, classroom.getNumberOfPeople(), "numberOfPeople not saved properly");
    }

    @Test
    public void getRoomReservationListTest() {
        assertNotNull(classroom.getRoomReservationList(), "Expected not null");
        assertEquals(0, classroom.getRoomReservationList().size(), "Expected an empty list");
    }

    @Test
    public void addReservationToListTest() {
        classroom.addReservationToList(roomReservation);
        assertNotNull(classroom.getRoomReservationList(), "Expected not null");
        assertEquals(1, classroom.getRoomReservationList().size(), "Expected 1 reservation");
        assertEquals(roomReservation, classroom.getRoomReservationList().get(0),
                "Wrong reservation saved");
    }

    /**
     * Equals testing.
     */
    @Test
    public void equalsTest() {
        assertTrue(classroom.equals(classroom2), "Should be equal");
        assertEquals(classroom, classroom2, "Should be equal");
    }

    @Test
    public void equalsTestSame() {
        assertTrue(classroom.equals(classroom), "same classrooms should be equal");
        assertSame(classroom, classroom, "classrooms should be same");
    }

    @Test
    public void equalsTestNotEqual() {
        ClassRoom classroom2 = new ClassRoom(
                "testName2",
                building2,
                10
        );
        assertFalse(classroom.equals(classroom2), "Classrooms should not be equal");
        assertNotEquals(classroom, classroom2, "Classrooms should not be equal");
    }

    @Test
    public void equalsTestAttributesRoomName() {
        ClassRoom classroom2 = new ClassRoom(
                "testName2",
                building,
                5
        );
        assertFalse(classroom.equals(classroom2),
                "Classrooms should not be equal, room name different");
        assertNotEquals(classroom, classroom2,
                "Classrooms should not be equal, room name different");
    }

    @Test
    public void equalsTestAttributesBuilding() {
        ClassRoom classroom2 = new ClassRoom(
                "testName",
                building2,
                5
        );
        assertFalse(classroom.equals(classroom2),
                "Classrooms should not be equal, different building");
        assertNotEquals(classroom, classroom2,
                "Classrooms should not be equal, differentbuilding");
    }

}
