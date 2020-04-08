package nl.tudelft.oopp.demo.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class OrderTest {

    private FoodVendor testFoodVendor = new FoodVendor("testName");

    private FoodMenu testFoodMenu = new FoodMenu(
            123L,
            "testName",
            "testCategory",
            123,
            testFoodVendor
    );

    private MainOrder testMainOrder = new MainOrder("testName");

    private Order testOrder = new Order(testMainOrder, 123, testFoodMenu, "userTest", 10);

    //Testing
    @Test
    public void orderConstructorTest() {
        assertNotNull(testOrder, "Expected not null");
        assertEquals(123, testOrder.getQuantity(), "quantity saved incorrectly");
        assertEquals(testFoodMenu, testOrder.getFoodMenu(), "FoodMenu saved incorrecly");
    }

    @Test
    public void getQuantityTest() {
        assertEquals(123, testOrder.getQuantity(), "getQuantity incorrect");
    }

    @Test
    public void setQuantityTest() {
        testOrder.setQuantity(321);
        assertEquals(321, testOrder.getQuantity(), "setQuantity incorrect");
    }

    @Test
    public void getFoodMenuTest() {
        assertEquals(testFoodMenu, testOrder.getFoodMenu(), "getFoodMenu incorrect");
    }

    @Test
    public void setFoodMenuTest() {
        FoodMenu testFoodMenu2 = new FoodMenu(
                123L,
                "testName2",
                "testCategory",
                123,
                testFoodVendor
        );
        testOrder.setFoodMenu(testFoodMenu2);
        assertEquals(testFoodMenu2, testOrder.getFoodMenu(), "setFoodMenu incorrect");
    }


    //EqualsTesting
    @Test
    public void equalsTest() {
        Order testOrder2 = new Order(testMainOrder, 123, testFoodMenu, "userTest2", 11);
        assertTrue(testOrder.equals(testOrder2), "orders with the same foodmenu should be equal");
    }

    @Test
    public void equalsTestSame() {
        assertTrue(testOrder.equals(testOrder), "orders with the same foodmenu should be equal");
    }

    @Test
    public void equalsTestNotEqual() {
        FoodMenu testFoodMenu2 = new FoodMenu(
                1234L,
                "testName2",
                "testCategory",
                123,
                testFoodVendor
        );
        Order testOrder2 = new Order(testMainOrder, 123, testFoodMenu2, "userTest", 10);
        assertFalse(testOrder.equals(testOrder2),
                "orders with different food menus should not be equal");
    }
}