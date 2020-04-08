package nl.tudelft.oopp.demo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import nl.tudelft.oopp.demo.repositories.BuildingReposity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class BuildingTest {

    @Autowired
    private BuildingReposity buRep;

    private Building building = new Building(
            "testHours",
            "testName",
            "testDepartment",
            "testLocation",
            5
    );
    private Building building2 = new Building(
            "testHours",
            "testName",
            "testDepartment",
            "testLocation",
            5
    );
    private ClassRoom classroom = new ClassRoom(
            "test",
            building,
            50
    );

    /**
     * Testing.
     */

    @Test
    public void buildingConstructorTestEmpty() {
        Building emptyConstructorBuilding = new Building();
        assertNotNull(emptyConstructorBuilding, "Expected not null");
    }

    @Test
    public void buildingConstructorTest() {
        assertNotNull(building, "Expected not null");
        assertEquals("testHours", building.getOpeningHours(), "Not properly saved hours");
        assertEquals("testName", building.getBuildingName(), "Not properly saved name");
        assertEquals("testDepartment", building.getDepartment(), "Not properly saved Department");
        assertEquals("testLocation", building.getLocation(), "Not properly saved location");
        assertEquals(5, building.getNumberOfAvailablebikes(), "Not properly saved availableBikes");
    }

    @Test
    public void getIdTest() {
        buRep.save(building);
        assertNotNull(building.getId(), "Id not generated");
    }

    @Test
    public void setIdTest() {
        buRep.save(building);
        building.setId(123L);
        assertEquals(123L, building.getId(), "setID is not functioning");
    }

    @Test
    public void getOpeningHoursTest() {
        assertEquals("testHours", building.getOpeningHours(), "Not properly saved hours");
    }

    @Test
    public void getBuildingNameTest() {
        assertEquals("testName", building.getBuildingName(), "Not properly saved name");
    }

    @Test
    public void getDepartmentTest() {
        assertEquals("testDepartment", building.getDepartment(), "Not properly saved Department");
    }

    @Test
    public void getLocationTest() {
        assertEquals("testLocation", building.getLocation(), "Not properly saved location");
    }

    @Test
    public void getNumberOfAvailableBikesTest() {
        assertEquals(5, building.getNumberOfAvailablebikes(), "Not properly saved availableBikes");
    }

    @Test
    public void getClassRoomSetTest() {
        assertNotNull(building.getClassRoomSet(), "Should not be null");
        assertEquals(0, building.getClassRoomSet().size(), "Should have an empty classroomset");
    }

    @Test
    public void addClassRoomToSetTest() {
        assertNotNull(building.getClassRoomSet(), "Should not be null");
        assertEquals(0, building.getClassRoomSet().size(), "Should have an empty classroomset");
        building.addClassroomToSet(classroom);
        assertEquals(1, building.getClassRoomSet().size(), "Should be one classroom in the set");
        assertEquals(classroom, building.getClassRoomSet().get(0), "Classroom saved incorrectly");
    }

    @Test
    public void setNumberOfAvailableBikesTest() {
        building.setNumberOfAvailablebikes(10);
        assertEquals(10, building.getNumberOfAvailablebikes(),
                "Number of available bikes did not change");
    }

    @Test
    public void setBuildingNameTest() {
        building.setBuildingName("NewName");
        assertEquals("NewName", building.getBuildingName(), "Not properly updated name");
    }

    @Test
    public void setDepartmentTest() {
        building.setDepartment("newDepartment");
        assertEquals("newDepartment", building.getDepartment(), "Not properly updated Department");
    }

    @Test
    public void setLocationTest() {
        building.setLocation("newLocation");
        assertEquals("newLocation", building.getLocation(), "Not properly updated location");
    }

    @Test
    public void setOpeningHoursTest() {
        building.setOpeningHours("newHours");
        assertEquals("newHours", building.getOpeningHours(), "Not properly saved hours");
    }

    /**
     * Equals Testing.
     */

    @Test
    public void equalsTest() {
        assertTrue(building.equals(building2), "Should be equals");
        assertEquals(building, building2, "Should be equals");
    }

    @Test
    public void equalsTestSame() {
        assertTrue(building.equals(building), "Should be equals");
        assertSame(building, building, "Should be same");
    }

    @Test
    public void equalsTestNotEqual() {
        Building buildingNotEqual = new Building(
                "testHours2",
                "testName2",
                "testDepartment2",
                "testLocation2",
                7
        );
        assertFalse(building.equals(buildingNotEqual), "Should not be equals");
        assertNotEquals(building, buildingNotEqual, "Should not be equals");
    }

    @Test
    public void equalsTestDifferentAttributesName() {
        building2.setBuildingName("testName2");
        assertFalse(building.equals(building2), "Should not be equal, Name different");
    }

    @Test
    public void equalsTestDifferentAttributesDepartment() {
        building2.setDepartment("testDepartment2");
        assertFalse(building.equals(building2), "Should not be equal, Department different");
    }

    @Test
    public void equalsTestDifferentAttributesLocation() {
        building2.setLocation("testLocation2");
        assertFalse(building.equals(building2), "Should not be equal, Location different");
    }
}