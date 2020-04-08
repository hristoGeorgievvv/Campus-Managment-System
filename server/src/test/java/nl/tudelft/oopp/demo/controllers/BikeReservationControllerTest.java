package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertFalse;

import nl.tudelft.oopp.demo.entities.Building;
import nl.tudelft.oopp.demo.entities.User;
import nl.tudelft.oopp.demo.repositories.BuildingReposity;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import nl.tudelft.oopp.demo.services.BikeReservationModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class BikeReservationControllerTest {

    @Autowired
    private UserRepository userRep;
    @Autowired
    private BuildingReposity buildingRep;

    private User user = new User(
            "testName",
            "testType",
            "testEmail",
            "testPword",
            false
    );

    private Building building = new Building(
            "testHours",
            "testName",
            "testDepartment",
            "testLocation",
            5
    );

    private BikeReservationModel bikeReservationModel = new BikeReservationModel(
            "testName",
            "testName"
    );

    @Test
    public void userHasBikeTest() {
        userRep.save(user);
        BikeReservationController myBrC = new BikeReservationController(buildingRep, userRep);
        assertFalse(myBrC.userHasBike("testName"), "user without a bike should give false");
    }

    //    @Test
    //    public void changeOrDeleteReservation() {
    //        userRep.save(user);
    //        buildingRep.save(building);
    //        BikeReservationController myBRC = new BikeReservationController(buildingRep, userRep);
    //        System.out.println(buildingRep.findAllByBuildingName("Test_Name"));
    //        assertTrue(myBRC.changeOrDeleteReservation(bikeReservationModel),
    //        "User bike check should now be true");
    //        assertTrue(userRep.findUser("testName").getBike() ,"user should now have a bike");
    //        assertEquals(4, buildingRep.findAll().get(0).getNumberOfAvailablebikes(),
    //        "Should be one less bike to the building");
    //    }
}
