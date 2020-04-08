package nl.tudelft.oopp.demo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class OrderTest {

    User user = new User("TestUser", "testType", "testEmail", "testattr", false);
    ArrayList<Order> list = new ArrayList<>();
    private Building building = new Building(
            "testHours",
            "testName",
            "testDepartment",
            "testLocation",
            5
    );
    private ClassRoom classroom = new ClassRoom(
            "testName",
            building,
            5
    );
    private TimeSlots timeslot = new TimeSlots("testDuration");
    private RoomReservation roomReservation = new RoomReservation(
            "testDate",
            user,
            classroom,
            timeslot
    );

    private FoodVendor vendor = new FoodVendor();

    FoodMenu menu = new FoodMenu(
            "testFood",
            "testCategory",
            5.00,
            vendor
    );

    Order order1 = new Order(user, 2.00, menu, roomReservation);
    Order order2 = new Order(user, 2.00, menu, roomReservation);

    /**
     * testing.
     */

    @Test
    public void constructorTestEmpty() {
        Order order = new Order();
        assertNotNull(order, "Expected not null");
    }

    @Test
    public void constructorTest() {
        assertNotNull(order1, "Expected not null");
        assertEquals(2.00, order1.getQuantity(), "Quantity not properly saved");
        assertEquals(menu, order1.getFoodMenu(), "FoodMenu not properly saved");
    }

    @Test
    public void getQuantityTest() {
        assertEquals(2.00, order1.getQuantity(), "getQuantity not correct");
    }

    @Test
    public void getFoodMenuTest() {
        assertEquals(menu, order1.getFoodMenu(), "getFoodMenu not correct");
    }

    @Test
    public void getUserTest() {
        assertEquals(user, order1.getUser(), "getUser not correct");
    }

    @Test
    public void getReservationTest() {
        assertEquals(roomReservation, order1.getReservation(), "getReservation not correct");
    }

    @Test
    public void setQuantityTest() {
        order1.setQuantity(3.22);
        assertEquals(3.22, order1.getQuantity(), "SetQuantity not correct");
    }

    @Test
    public void setFoodMenuTest() {
        FoodMenu menu2 = new FoodMenu("diff", "diff", 10.00, vendor);
        order1.setFoodMenu(menu2);
        assertEquals(menu2, order1.getFoodMenu(), "setFoodMenu not correct");
    }

    @Test
    public void setUserTest() {
        User user2 = new User("diff", "diff", "diff", "diff", false);
        order1.setUser(user2);
        assertEquals(user2, order1.getUser(), "setUser not correct");
    }

    @Test
    public void setReservationTest() {
        User user2 = new User("diff", "diff", "diff", "diff", false);
        RoomReservation roomReservation2 = new RoomReservation(
                "DifferentDate",
                user2,
                classroom,
                timeslot
        );
        order1.setReservation(roomReservation2);
        assertEquals(roomReservation2, order1.getReservation(), "setReservation not correct");
    }

    @Test
    public void equalsSameTest() {
        assertTrue(order1.equals(order1), "Should be equal to itself");
    }

    @Test
    public void equalsSameAttributesTest() {
        assertTrue(order1.equals(order2), "Should be equal to itself");
    }

    @Test
    public void equalsDifferentQuantityTest() {
        order2.setQuantity(5.00);
        assertFalse(order1.equals(order2), "MainOrders with different users should be different");
    }

    @Test
    public void equalsDifferentUserTest() {
        User user2 = new User("diff", "diff", "diff", "diff", false);
        order2.setUser(user2);
        assertFalse(order1.equals(order2), "Orders with different Users should be different");
    }

    @Test
    public void equalsDifferentMenuTest() {
        FoodMenu menu2 = new FoodMenu("diff",
                "diff",
                2.00,
                vendor);
        order2.setFoodMenu(menu2);
        assertFalse(order1.equals(order2), "Orders with different menus should be different");
    }

    @Test
    public void equalsDifferentReservationTest() {
        User user2 = new User("diff", "diff", "diff", "diff", false);
        RoomReservation roomReservation2 = new RoomReservation(
                "DifferentDate",
                user2,
                classroom,
                timeslot
        );
        order2.setReservation(roomReservation2);
        assertFalse(order1.equals(order2), "Orders with different menus should be different");
    }

    @Test
    public void equalsEmptyConstructorTest() {
        Order emptyConstructor = new Order();
        Order emptyConstructor2 = new Order();
        assertTrue(emptyConstructor.equals(emptyConstructor2),
                "Objects with an empty constructor should be equal");
    }
}
