package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import nl.tudelft.oopp.demo.entities.FoodMenu;
import nl.tudelft.oopp.demo.entities.FoodVendor;
import nl.tudelft.oopp.demo.repositories.FoodMenuRepository;
import nl.tudelft.oopp.demo.repositories.FoodVendorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class FoodMenuControllerTest {

    @Autowired
    private FoodMenuRepository foodMenuRep;

    @Autowired
    private FoodVendorRepository foodVendorRep;

    private FoodVendor foodVendor = new FoodVendor("testName");

    private FoodMenu foodMenu = new FoodMenu("testName", "testCategory", 123, foodVendor);

    @Test
    public void sendFoodMenusTest() {
        foodMenuRep.save(foodMenu);
        foodVendorRep.save(foodVendor);
        FoodMenuController myFmC = new FoodMenuController(foodMenuRep);
        List<FoodMenu> expected = new ArrayList<FoodMenu>();
        expected.add(foodMenu);
        assertEquals(expected, myFmC.sendFoodMenus(), "sendFoodMenu is incorrect");
    }
}
