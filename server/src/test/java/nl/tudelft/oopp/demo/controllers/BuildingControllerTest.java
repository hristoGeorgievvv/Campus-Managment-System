package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import nl.tudelft.oopp.demo.entities.Building;
import nl.tudelft.oopp.demo.repositories.BuildingReposity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class BuildingControllerTest {

    @Autowired
    private BuildingReposity buildingReposity;

    private Building building = new Building(
            "Test_Name",
            "ToBeDeleted",
            "Test_Location",
            "TestingHours",
            5
    );

    private Building building2 = new Building(
            "Test_Name",
            "Test_Department2",
            "Test_Location",
            "TestingHours",
            5
    );

    /**
     * Testing sendBuildings Method in BuildingController.
     */
    @Test
    public void sendBuildingsTestEmpty() {
        BuildingController myBC = new BuildingController(buildingReposity);
        assertEquals(myBC.sendBuildings(),"No Buildings Found!", "Expected an error message");
    }

    /**
     * Testing sendBuildings Method in BuildingController.
     */
    @Test
    public void sendBuildingsTestOneBuilding() {
        buildingReposity.save(building);
        BuildingController myBC = new BuildingController(buildingReposity);
        Object buildings = myBC.sendBuildings();
        List<Building> buildingsExpected = new ArrayList<Building>();
        buildingsExpected.add(building);
        assertNotNull(buildings, "Expected a list of buildings");
        assertEquals(buildings, buildingsExpected, "List of buildings incorrect");
    }

    /**
     * Testing sendBuildings Method in BuildingController.
     */
    @Test
    public void sendBuildingsTestManyBuildings() {
        buildingReposity.save(building);
        buildingReposity.save(building2);
        BuildingController myBC = new BuildingController(buildingReposity);
        Object buildings = myBC.sendBuildings();
        List<Building> buildingsExpected = new ArrayList<Building>();
        buildingsExpected.add(building);
        buildingsExpected.add(building2);
        assertNotNull(buildings, "Expected not null");
        assertTrue(buildings instanceof List, "Expected a List");
        assertEquals(buildings, buildingsExpected, "List of buildings incorrect");
    }


    /**
     * Testing createNewBuilding Method in BuildingController.
     */
    @Test
    public void createNewBuildingTest() {
        BuildingController myBC = new BuildingController(buildingReposity);
        myBC.createNewBuilding(building);
        assertEquals(1, buildingReposity.findAll().size(),
                "Expected 1 new building in repository");
        assertEquals(building, buildingReposity.findAll().get(0),
                "Expected new building in repository");
    }


    /*Null test not included as in string doc says param must never be null..
      dont know whether to keep or not.
     */
    /*@Test
    public void createNewBuildingTestNull(){
        BuildingController myBC = new BuildingController(buildingReposity);
        assertNull(myBC.createNewBuilding(null), "Expected null return");
        assertTrue(buildingReposity.findAll().isEmpty(), "Expected an empty repository");

    }*/


    /**
     * Testing deleteBuilding in BuildingController.
     */
    @Test
    public void deleteBuildingTest() {
        buildingReposity.save(building);
        BuildingController myBC = new BuildingController(buildingReposity);
        myBC.deleteBuilding("ToBeDeleted");
        assertEquals(0, buildingReposity.findAll().size(), "Expected building to be deleted");
    }

    /**
     * Testing deleteBuilding in BuildingController.
     */
    @Test
    public void deleteBuildingTestEmptyRepository() {
        BuildingController myBC = new BuildingController(buildingReposity);
        myBC.deleteBuilding("Nothing There");
        //No assert clause, because only checking for exceptions
    }

    /**
     * Testing deleteBuilding in BuildingController.
     */
    @Test
    public void deleteBuildingTestMultipleBuildings() {
        buildingReposity.save(building);
        buildingReposity.save(building2);
        BuildingController myBC = new BuildingController(buildingReposity);
        myBC.deleteBuilding("ToBeDeleted");
        assertEquals(1, buildingReposity.findAll().size(), "Expected building to be deleted");
        assertEquals(building2, buildingReposity.findAll().get(0),
                "Expected building2 not to be deleted");
    }


    /**
     * UpdateBuilding testing from BuildingController
     * Dont Fully understand how this works... but for now exception testing
     */
    @Test
    public void updateBuildingTest() {
        Building building = new Building(
                "Test_Name",
                "ToBeUpdated",
                "Test_Location",
                "TestingHours",
                5
        );
        buildingReposity.save(building);
        BuildingController myBC = new BuildingController(buildingReposity);
        myBC.updateBuilding(building);
    }
}