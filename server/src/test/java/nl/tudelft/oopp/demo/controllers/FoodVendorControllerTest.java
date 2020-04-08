package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import nl.tudelft.oopp.demo.entities.FoodVendor;
import nl.tudelft.oopp.demo.repositories.FoodVendorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class FoodVendorControllerTest {

    @Autowired
    private FoodVendorRepository foodVendorRep;

    private FoodVendor foodVendor = new FoodVendor("testName");

    @Test
    public void sendFoodVendorsTest() {
        foodVendorRep.save(foodVendor);
        FoodVendorController myFvC = new FoodVendorController(foodVendorRep);
        List<FoodVendor> expected = new ArrayList<FoodVendor>();
        expected.add(foodVendor);
        assertEquals(expected, myFvC.sendFoodVendors(), "sendFoodVendor is incorrect");
    }

}
