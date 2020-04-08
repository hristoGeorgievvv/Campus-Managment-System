package nl.tudelft.oopp.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import nl.tudelft.oopp.demo.entities.FoodMenu;
import nl.tudelft.oopp.demo.entities.FoodVendor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class OrderRequestModelTest {

    FoodVendor vendor = new FoodVendor("Jeff");
    FoodMenu menu = new FoodMenu("TestFood", "TestCategory", 5.00, vendor);

    OrderRequestModel order1 = new OrderRequestModel(1.00, menu, "TestUser", 5);
    OrderRequestModel order2 = new OrderRequestModel(1.00, menu, "TestUser", 5);

    @Test
    public void constructorTest() {
        assertEquals(1.00, order1.getQuantity(), "Quantity not properly saved");
        assertEquals(menu, order1.getFoodMenu(), "FoodMenu not properly saved");
        assertEquals("TestUser", order1.getUsername(), "UserName not properly saved");
        assertEquals(5, order1.getResId(), "ResID not properly saved");
    }

    @Test
    public void getQuantityTest() {
        assertEquals(1.00, order1.getQuantity(), "getQuantity not correct");
    }

    @Test
    public void getFoodMenuTest() {
        assertEquals(menu, order1.getFoodMenu(), "getFoodMenu not correct");
    }

    @Test
    public void getUserName() {
        assertEquals("TestUser", order1.getUsername(), "getUserName not correct");
    }

    @Test
    public void getResId() {
        assertEquals(5, order1.getResId(), "getResID not correct");
    }

    @Test
    public void setQuantityTest() {
        order1.setQuantity(5.00);
        assertEquals(5.00, order1.getQuantity(), "setQuantity not correct");
        assertNotEquals(1.00, order1.getQuantity(), "Quanitity should be different");
    }

    @Test
    public void setFoodMenuTest() {
        FoodMenu menu2 = new FoodMenu("diff", "diff", 5.00, vendor);
        order1.setFoodMenu(menu2);
        assertEquals(menu2, order1.getFoodMenu(), "setFoodMenu not correct");
        assertNotEquals(menu, order1.getFoodMenu(), "Menu should be different");
    }

    @Test
    public void equalsSameTest() {
        assertTrue(order1.equals(order1), "OrderRequestModels should be equal to themselves");
    }

    @Test
    public void equalsSameAttributesTest() {
        assertTrue(order1.equals(order2), "OrderRequestModels with the same attributes "
                + "should be equal");
    }

    @Test
    public void equalsDifferentQuantityTest() {
        order1.setQuantity(2.00);
        assertFalse(order1.equals(order2), "OrderRequestModels with different quantities "
                + "should not be equal");
    }

    @Test
    public void equalsDifferentMenuTest() {
        FoodMenu menu2 = new FoodMenu("Diff", "Diff", 522.00, vendor);
        order1.setFoodMenu(menu2);
        assertFalse(order1.equals(order2), "OrderRequestModels with different menus "
                + "should not be equal");
    }

    @Test
    public void equalsDifferentUserNameTest() {
        order1 = new OrderRequestModel(1.00, menu, "Diff", 5);
        assertFalse(order1.equals(order2), "OrderRequestModels with different userNames "
                + "should not be equal");
    }

    @Test
    public void equalsDifferentResIdTest() {
        order1 = new OrderRequestModel(1.00, menu, "Diff", 1234);
        assertFalse(order1.equals(order2), "OrderRequestModels with different Resident ID's "
                + "should not be equal");
    }
}
