package nl.tudelft.oopp.demo.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;




public class MainOrderTest {

    private MainOrder testMainOrder = new MainOrder("testName");
    //Tests start here

    @Test
    public void mainOrderConstructorTest() {
        assertNotNull(testMainOrder, "Expected not null");
        assertEquals("testName", testMainOrder.getUser(), "user saved incorrectly");
        assertEquals(0, testMainOrder.getOrders().size(), "Orders not correctly initiated");
    }

    @Test
    public void getUserTest() {
        assertEquals("testName", testMainOrder.getUser(), "getUser incorrect");
    }

    @Test
    public void setUserTest() {
        testMainOrder.setUser("newName");
        assertEquals("newName", testMainOrder.getUser(), "setUser incorrect");
    }

    @Test
    public void getOrdersTest() {
        assertEquals(0, testMainOrder.getOrders().size(), "getOrders not correct");
    }

    @Test
    public void setOrdersTest() {
        List<Order> newOrders = new ArrayList<Order>();
        testMainOrder.setOrders(newOrders);
        assertEquals(newOrders, testMainOrder.getOrders(), "setOrders not correct");
    }

    //Equals testing
    @Test
    public void equalsTest() {
        MainOrder testMainOrder2 = new MainOrder("testName");
        List<Order> newOrders = new ArrayList<Order>();
        testMainOrder.setOrders(newOrders);
        testMainOrder2.setOrders(newOrders);
        assertTrue(testMainOrder.equals(testMainOrder2),
                "MainOrders with same user and order should be Equal");
    }

    @Test
    public void equalsTestSame() {
        assertTrue(testMainOrder.equals(testMainOrder), "Same MainOrder should be Equal");
    }

    @Test
    public void equalsTestNotEqual() {
        MainOrder testMainOrder2 = new MainOrder("testName2");
        assertFalse(testMainOrder.equals(testMainOrder2),
                "Mainorders with different Users should be different");
    }

}