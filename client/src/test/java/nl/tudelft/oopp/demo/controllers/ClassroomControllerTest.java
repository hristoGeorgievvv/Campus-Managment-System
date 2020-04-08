package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertNull;

import nl.tudelft.oopp.demo.models.Building;
import org.junit.jupiter.api.Test;


public class ClassroomControllerTest {

    private Building testBuilding = new Building(
            "testHours",
            "testName",
            "testDepartment",
            "testLocation",
            5
    );

    @Test
    public void generateDataTest() {
        assertNull(ClassroomController.generateData(), "Expected null, with invalid http response");
    }

    @Test
    public void getClassroomsTest() {
        assertNull(ClassroomController.getClassRooms(testBuilding),
                "Expected null, with invalid http response");
    }
}
