package nl.tudelft.oopp.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class BikeReservationModelTest {

    BikeReservationModel model1 = new BikeReservationModel("TestUser", "TestBuilding");
    BikeReservationModel model2 = new BikeReservationModel("TestUser", "TestBuilding");

    @Test
    public void constructorTest() {
        assertEquals("TestUser", model1.getUserName(), "userName not properly saved");
        assertEquals("TestBuilding", model1.getBuildingName(), "userName not properly saved");
    }

    @Test
    public void getUserNameTest() {
        assertEquals("TestUser", model1.getUserName(), "get Username not correct");
    }

    @Test
    public void getBuildingNameTest() {
        assertEquals("TestBuilding", model1.getBuildingName(), "get Building name not correct");
    }

    @Test
    public void equalsSameTest() {
        assertTrue(model1.equals(model1), "A Bike reservation model should be equal to itself");
    }

    @Test
    public void equalsSameAttributesTest() {
        assertTrue(model1.equals(model2), "Should be equal if attributes are equal");
    }

    @Test
    public void equalsDifferentUserNameTest() {
        model2 = new BikeReservationModel("diff", "TestBuilding");
        assertFalse(model1.equals(model2), "A Bike reservation model with "
                + "different userNames should not be equal");
    }

    @Test
    public void equalsDifferentBuildingNameTest() {
        model2 = new BikeReservationModel("TestUser", "diff");
        assertFalse(model1.equals(model2), "A Bike reservation model with "
                + "different buildingNames should not be equal");
    }
}
