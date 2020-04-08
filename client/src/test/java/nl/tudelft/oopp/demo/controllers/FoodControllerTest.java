package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertNull;

import nl.tudelft.oopp.demo.models.FoodVendor;
import org.junit.jupiter.api.Test;


public class FoodControllerTest {

    private FoodVendor testVendor = new FoodVendor(123L, "testVendor");

    @Test
    public void getFoodMenusTest() {
        assertNull(FoodController.getFoodMenus(testVendor),
                "Expected null, with invalid http response");
    }

    @Test
    public void getRestaurantsTest() {
        assertNull(FoodController.getRestaurants(),
                "Expected null, with invalid http response");
    }

    @Test
    public void generateDataTest() {
        assertNull(FoodController.generateData(),
                "Expected null, with invalid http response");
    }

    @Test
    public void getOrdersTest() {
        assertNull(FoodController.getOrders(),
                "Expected null, with invalid http response");
    }
}