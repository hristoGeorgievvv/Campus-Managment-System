package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import nl.tudelft.oopp.demo.entities.Building;
import nl.tudelft.oopp.demo.entities.ClassRoom;
import nl.tudelft.oopp.demo.repositories.BuildingReposity;
import nl.tudelft.oopp.demo.repositories.ClassRoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class ClassroomControllerTest {

    @Autowired
    private ClassRoomRepository classroomRepository;
    @Autowired
    private BuildingReposity buildingReposity;

    private  Building testbuilding = new Building(
            "Test_Name",
            "Test_Department",
            "Test_Location",
            "TestingHours",
            5
    );

    private  ClassRoom classroom = new ClassRoom(
            "test",
            testbuilding,
            50
    );

    private ClassRoom classroom2 = new ClassRoom(
            "test2",
            testbuilding,
            5
    );


    /**
     * Testing getAllBuildings Method in BuildingController.
     */
    @Test
    public void getAllClassroomsTestEmpty() {
        ClassroomController myCC = new ClassroomController(classroomRepository, buildingReposity);
        assertTrue(myCC.getAllClassrooms().isEmpty(),"Expected an empty list");
    }

    @Test
    public void getAllClassroomsTestOneClassroom() {
        buildingReposity.save(testbuilding);
        classroomRepository.save(classroom);
        ClassroomController myCC = new ClassroomController(classroomRepository, buildingReposity);
        assertFalse(myCC.getAllClassrooms().isEmpty());
        int listSize = myCC.getAllClassrooms().size();
        assertEquals(1, listSize, "Expected size to be 1 but  was: " + listSize);
        List<ClassRoom> classroomList = myCC.getAllClassrooms();
        assertEquals(classroom, classroomList.get(0), "Expected first building in list to be same");
    }

    @Test
    public void getAllClassroomsTestManyClassroom() {
        buildingReposity.save(testbuilding);
        classroomRepository.save(classroom);
        classroomRepository.save(classroom2);
        ClassroomController myCC = new ClassroomController(classroomRepository, buildingReposity);
        assertFalse(myCC.getAllClassrooms().isEmpty());
        int listSize = myCC.getAllClassrooms().size();
        assertEquals(2, listSize, "Expected size to be 2 but  was: " + listSize);
        List<ClassRoom> classroomList = myCC.getAllClassrooms();
        assertEquals(classroom, classroomList.get(0),
                "Expected first building in list is incorrect");
        assertEquals(classroom2, classroomList.get(1),
                "Expected Second building in list is incorrect");
    }

    /**
     * SendClassroom testing in ClassRoomControllerTest.
     */

    @Test
    public void sendClassroomsTestEmpty() {
        ClassroomController myCC = new ClassroomController(classroomRepository, buildingReposity);
        assertEquals(myCC.sendClassrooms(), "No Classrooms Found!", "Expected Error message");
    }

    @Test
    public void sendBuildingsTestManyBuildings() {
        buildingReposity.save(testbuilding);
        classroomRepository.save(classroom);
        classroomRepository.save(classroom2);
        ClassroomController myCC = new ClassroomController(classroomRepository, buildingReposity);
        Object classrooms = myCC.sendClassrooms();
        List<ClassRoom> classroomsExpected = new ArrayList<ClassRoom>();
        classroomsExpected.add(classroom);
        classroomsExpected.add(classroom2);
        assertNotNull(classrooms, "Expected not null");
        assertTrue(classrooms instanceof List, "Expected a List");
        assertEquals(classrooms, classroomsExpected, "List of Classrooms incorrect");
    }

    /**
     * Testing SendClassroomsByBuildingID.
     */

    @Test
    public void sendClassroomsByBuildingIdTest() {
        buildingReposity.save(testbuilding);
        classroomRepository.save(classroom);
        classroomRepository.save(classroom2);
        testbuilding.addClassroomToSet(classroom);
        testbuilding.addClassroomToSet(classroom2);
        ClassroomController myCC = new ClassroomController(classroomRepository, buildingReposity);
        //This is used to generate a hashmap in classroomcontroller as
        // injecting one does not work for some reason
        try {
            myCC.run("");
        } catch (Exception e) {
            fail("Exception executing run");
        }
        List<ClassRoom> expected = myCC.getHashMap().get("Test_Department");
        Object actual = myCC.sendClassroomsByBuildingId("Test_Department");
        assertNotNull(actual, "Expected not null");
        assertTrue(actual instanceof List, "Expected a list");
        assertEquals(actual, expected, "List of classrooms incorrect");
    }

    @Test
    public void sendClassroomsByBuildingIdTestWrongName() {
        Building testbuilding = new Building(
                "Test_Name",
                "Test_Department",
                "Test_Location",
                "TestingHours",
                5
        );
        buildingReposity.save(testbuilding);
        ClassRoom classroom = new ClassRoom(
                "test",
                testbuilding,
                4
        );
        ClassRoom classroom2 = new ClassRoom(
                "test2",
                testbuilding,
                5
        );
        classroomRepository.save(classroom);
        classroomRepository.save(classroom2);
        testbuilding.addClassroomToSet(classroom);
        testbuilding.addClassroomToSet(classroom2);
        ClassroomController myCC = new ClassroomController(classroomRepository, buildingReposity);
        //This is used to generate a hashmap in classroomcontroller as
        // injecting one does not work for some reason
        try {
            myCC.run("");
        } catch (Exception e) {
            fail("Exception executing run");
        }
        Object actual = myCC.sendClassroomsByBuildingId("WrongName");
        assertTrue(actual instanceof String, "Expected a String");
        assertEquals(actual, "No Classrooms Found!", "Expected error Message");
    }

    @Test
    public void sendClassroomsByBuildingIdTestNoClasses() {
        buildingReposity.save(testbuilding);
        ClassroomController myCC = new ClassroomController(classroomRepository, buildingReposity);
        //This is used to generate a hashmap in classroomcontroller as
        // injecting one does not work for some reason
        try {
            myCC.run("");
        } catch (Exception e) {
            fail("Exception executing run");
        }
        Object actual = myCC.sendClassroomsByBuildingId("Test_Department");
        assertTrue(actual instanceof String, "Expected a String");
        assertEquals(actual, "No Classrooms Found!", "Expected error Message");
    }
}