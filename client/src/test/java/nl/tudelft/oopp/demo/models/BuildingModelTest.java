package nl.tudelft.oopp.demo.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import nl.tudelft.oopp.demo.models.Building;
import org.junit.jupiter.api.Test;

public class BuildingModelTest {

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
    private Building building3 = new Building(
            "testhr",
            "testnm",
            "testdpt",
            "testloc",
            4
    );

    @Test
    public void equalsTestSame() {
        assertEquals(building,building,
                "Equals method should return "
                        + "true if the object is the same");
    }

    @Test
    public void equalsTestDiff() {
        assertEquals(building,building2,
                "Equals method should "
                        + "return true if relevant values are the same");
    }

    @Test
    public void notEquals() {
        assertNotEquals(building,building3,
                "Equals method should return"
                        + "false if relevant values are different");
    }

    @Test
    public void getBikes() {
        assertEquals(5, building.getNumberOfAvailablebikes(),
                "building.getNumberOfAvailablebikes failed"
                        + "to produce correct value");
        assertEquals(4, building3.getNumberOfAvailablebikes(),
                "building.getNumberOfAvailablebikes failed"
                        + "to produce correct value");
    }

    @Test
    public void getBName() {
        assertEquals("testName", building.getBuildingName(),
                "building.getBuildingName failed"
                        + "to produce correct value");
        assertEquals("testnm", building3.getBuildingName(),
                "building.getBuildingName failed"
                        + "to produce correct value");
    }

    @Test
    public void getBDpt() {
        assertEquals("testDepartment", building.getDepartment(),
                "building.getDepartment failed"
                        + "to produce correct value");
        assertEquals("testdpt", building3.getDepartment(),
                "building.getDepartment failed"
                        + "to produce correct value");
    }

    @Test
    public void getBLocation() {
        assertEquals("testLocation", building.getLocation(),
                "building.getLocation failed"
                        + "to produce correct value");
        assertEquals("testloc", building3.getLocation(),
                "building.getLocation failed"
                        + "to produce correct value");
    }

    @Test
    public void getBHours() {
        assertEquals("testHours", building.getOpeningHours(),
                "building.getOpeningHours failed"
                        + "to produce correct value");
        assertEquals("testhr", building3.getOpeningHours(),
                "building.getOpeningHours failed"
                        + "to produce correct value");
    }

}
