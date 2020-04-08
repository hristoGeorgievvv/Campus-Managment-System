package nl.tudelft.oopp.demo.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class BikeReservationModelTest {
    private BikeReservationModel testBikeReservationModel = new BikeReservationModel(
            "testUsername",
            "testBuildingName"
    );

    //Tests start here
    @Test
    public void bikeReservationModelConstructorTest() {
        assertNotNull(testBikeReservationModel, "Expected not null");
        assertEquals("testUsername", testBikeReservationModel.getUserName(),
                "Not properly saved Username");
        assertEquals("testBuildingName", testBikeReservationModel.getBuildingName(),
                "Not properly saved buildingname");
    }

    @Test
    public void getUserNameTest() {
        assertEquals("testUsername", testBikeReservationModel.getUserName(),
                "getUserName incorrect");
    }

    @Test
    public void getBuildingNameTest() {
        assertEquals("testBuildingName", testBikeReservationModel.getBuildingName(),
                "getBuildingName incorrect");
    }

    //Equals testing
    @Test
    public void equalsTest() {
        BikeReservationModel testBikeReservationModel2 = new BikeReservationModel(
                "testUsername",
                "testBuildingName"
        );
        assertTrue(testBikeReservationModel.equals(testBikeReservationModel2),
                "Expected equal objects to be eaqual");
    }

    @Test
    public void equalsTestSame() {
        assertTrue(testBikeReservationModel.equals(testBikeReservationModel),
                "expected the same object to be equal");
    }

    @Test
    public void equalsTestNotEqual() {
        BikeReservationModel testBikeReservationModel2 = new BikeReservationModel(
                "testUsername2",
                "testBuildingName2"
        );
        assertFalse(testBikeReservationModel.equals(testBikeReservationModel2));
    }

}