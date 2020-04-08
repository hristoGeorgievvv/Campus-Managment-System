package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import nl.tudelft.oopp.demo.entities.Building;
import nl.tudelft.oopp.demo.entities.ClassRoom;
import nl.tudelft.oopp.demo.entities.FoodMenu;
import nl.tudelft.oopp.demo.entities.FoodVendor;
import nl.tudelft.oopp.demo.entities.Order;
import nl.tudelft.oopp.demo.entities.RoomReservation;
import nl.tudelft.oopp.demo.entities.TimeSlots;
import nl.tudelft.oopp.demo.entities.User;
import nl.tudelft.oopp.demo.repositories.BuildingReposity;
import nl.tudelft.oopp.demo.repositories.ClassRoomRepository;
import nl.tudelft.oopp.demo.repositories.FoodMenuRepository;
import nl.tudelft.oopp.demo.repositories.FoodVendorRepository;
import nl.tudelft.oopp.demo.repositories.OrderRepository;
import nl.tudelft.oopp.demo.repositories.RoomReservationRepository;
import nl.tudelft.oopp.demo.repositories.TimeSlotsRepository;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import nl.tudelft.oopp.demo.services.OrderRequestModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class OrderControllerTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomReservationRepository roomReservationRepository;

    @Autowired
    private ClassRoomRepository classRep;

    @Autowired
    private TimeSlotsRepository timeRep;

    @Autowired
    private BuildingReposity buildingRep;

    @Autowired
    private FoodMenuRepository foodMenuRep;

    @Autowired
    private FoodVendorRepository foodVendorRep;

    private FoodVendor foodVendor = new FoodVendor("testVendor");

    private FoodMenu foodMenu = new FoodMenu("testName", "testCategory", 123, foodVendor);

    private User user = new User(
            "testUser",
            "testType",
            "testEmail",
            "testPword",false
    );
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

    @Test
    public void createNewOrderTest() {
        roomReservationRepository.save(roomReservation);
        userRepository.save(user);
        buildingRep.save(building);
        classRep.save(classroom);
        timeRep.save(timeslot);
        foodMenuRep.save(foodMenu);
        foodVendorRep.save(foodVendor);

        OrderRequestModel orderRequestModel = new OrderRequestModel(
                123,
                foodMenu,
                "testUser",
                roomReservation.getId()
        );
        OrderController orderController = new OrderController(orderRepository, userRepository,
                roomReservationRepository);
        Order expected = new Order(user, 123, foodMenu, roomReservation);
        assertEquals(expected, orderController.createNewOrder(orderRequestModel),
                "Should return order saved");
        assertEquals(expected, orderRepository.findAll().get(0),
                "Order should be saved in repository");
    }

    @Test
    public void sendOrdersTest() {
        Order order = new Order(user, 123, foodMenu, roomReservation);
        orderRepository.save(order);
        roomReservationRepository.save(roomReservation);
        userRepository.save(user);
        buildingRep.save(building);
        classRep.save(classroom);
        timeRep.save(timeslot);
        foodMenuRep.save(foodMenu);
        foodVendorRep.save(foodVendor);


        OrderController orderController = new OrderController(orderRepository, userRepository,
                roomReservationRepository);
        OrderRequestModel expected = new OrderRequestModel(
                123,
                foodMenu,
                "testUser",
                roomReservation.getId()
        );
        List<OrderRequestModel> expectedList = new ArrayList<OrderRequestModel>();
        expectedList.add(expected);
        assertTrue(orderController.sendOrders("testUser") instanceof List,
                "Should return a list");
        assertEquals(expectedList, orderController.sendOrders("testUser"),
                "Should return a list of orderRequestModels");
    }
}