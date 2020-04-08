package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import nl.tudelft.oopp.demo.models.Building;
import org.junit.jupiter.api.Test;


public class BuildingControllerTest {

    private Building testBuilding = new Building(
            "testHours",
            "testName",
            "testDepartment",
            "testLocation",
            5
    );

    @Test
    public void getBuildingsTest() {
        assertNull(BuildingController.getBuildings(), "Expected null, with invalid http response");
    }

    @Test
    public void getBuildingNamesTest() {
        assertNull(BuildingController.getBuildingNames(),
                "Expected null, with invalid http response");
    }

    @Test
    public void deleteBuildingTest() {
        assertFalse(BuildingController.deleteBuilding("testName"),
                "Expected false, with invalid http response");
    }

    @Test
    public void createBuildingTest() {
        assertFalse(BuildingController.createBuilding(testBuilding),
                "Expected False, with invalid http response");
    }

    @Test
    public void updateBuildingTest() {
        assertFalse(BuildingController.updateBuilding(testBuilding),
                "Expected False, with invalid http response");
    }
}
