package nl.tudelft.oopp.demo.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;




public class ClassroomModelTest {

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
            building,
            "room1",
            4,
            false,
            true,
            true
    );

    private ClassRoom classroom2 = new ClassRoom(
            building2,
            "room2",
            6,
            true,
            false,
            false
    );

    @Test
    void isBlackBoard() {
        assertTrue(classroom2.isBlackBoard(),
                "isBlackBoard produced incorrect value");
        assertFalse(classroom.isBlackBoard(),
                "isBlackBoard produced incorrect value");
    }

    @Test
    void isWhiteBoard() {
        assertTrue(classroom.isWhiteBoard(),
                "isWhiteBoard produced incorrect value");
        assertFalse(classroom2.isWhiteBoard(),
                "isWhiteBoard produced incorrect value");
    }

    @Test
    void isProjectorBoard() {
        assertTrue(classroom.isProjectorBoard(),
                "isProjectorBoard produced incorrect value");
        assertFalse(classroom2.isProjectorBoard(),
                "isProjectorBoard produced incorrect value");
    }

    @Test
    void getRoomName() {
        assertNotEquals(classroom.getRoomName(), classroom2.getRoomName(),
                "Different room names produced same value");
        assertEquals("room1", classroom.getRoomName(),
                "getRoomName produced incorrect value");
        assertEquals("room2", classroom2.getRoomName(),
                "getRoomName produced incorrect value");
    }

    @Test
    void getBuilding() {
        assertNotEquals(classroom.getBuilding(), classroom2.getBuilding(),
                "Different Buildings produced same value");
        assertEquals(building, classroom.getBuilding(),
                "getBuilding produced incorrect value");
        assertEquals(building2, classroom2.getBuilding(),
                "getBuilding produced incorrect value");
    }

    @Test
    void getNumberOfPeople() {
        assertNotEquals(classroom.getNumberOfPeople(), classroom2.getNumberOfPeople(),
                "Different # of people produced same value");
        assertEquals(4, classroom.getNumberOfPeople(),
                "getNumberOfPeople produced incorrect value");
        assertEquals(6, classroom2.getNumberOfPeople(),
                "getNumberOfPeople produced incorrect value");
    }

    @Test
    void setBuilding() {
        classroom.setBuilding(building2);
        assertEquals(classroom.getBuilding(), classroom2.getBuilding(),
                "setBuilding failed to set correctly");
    }

    @Test
    void setRoomName() {
        classroom.setRoomName("newRoomName");
        assertNotEquals("room1", classroom.getRoomName(),
                "setRoomName failed to set new name");
        assertEquals("newRoomName", classroom.getRoomName(),
                "setRoomName failed to set new name");
    }

    @Test
    void setNumberOfPeople() {
        classroom.setNumberOfPeople(6);
        assertEquals(classroom2.getNumberOfPeople(), classroom.getNumberOfPeople(),
                "setNumberOfPeople failed to set new value");
    }

    @Test
    void setBlackBoard() {
        classroom.setBlackBoard(true);
        assertTrue(classroom.isBlackBoard(),
                "setBlackboard failed to set new value");
    }

    @Test
    void setWhiteBoard() {
        classroom.setWhiteBoard(false);
        assertFalse(classroom.isWhiteBoard(),
                "setWhiteBoard failed to set new value");
    }

    @Test
    void setProjectorBoard() {
        classroom2.setProjectorBoard(true);
        assertTrue(classroom2.isProjectorBoard(),
                "setProjectorBoard failed to set new value");
    }

    @Test
    void testEquals() {
        assertEquals(classroom, classroom,
                "Same object should return true");
        assertNotEquals(classroom, classroom2,
                "Different objects should return false");
    }

    @Test
    void testEqualsAlternate() {
        ClassRoom classroom3 = new ClassRoom(
                1,
                "alternateName",
                4
        );
        ClassRoom classroom4 = new ClassRoom(
                2,
                "alternateName",
                4
        );
        assertEquals(classroom3, classroom3,
                "Same object should return true");
        assertNotEquals(classroom3, classroom4,
                "Different objects should return false");
    }
}
