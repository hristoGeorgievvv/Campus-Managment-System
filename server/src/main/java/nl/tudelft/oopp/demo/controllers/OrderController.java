package nl.tudelft.oopp.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import nl.tudelft.oopp.demo.entities.FoodMenu;
import nl.tudelft.oopp.demo.entities.Order;
import nl.tudelft.oopp.demo.entities.RoomReservation;
import nl.tudelft.oopp.demo.entities.User;
import nl.tudelft.oopp.demo.repositories.OrderRepository;
import nl.tudelft.oopp.demo.repositories.RoomReservationRepository;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import nl.tudelft.oopp.demo.services.OrderRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomReservationRepository roomReservationRepository;

    /**.
     * A constructor used in testing
     * @param orderRep OrderRepository
     * @param userRep UserRepository
     * @param roomResrvationRep RoomReservationRepository
     */
    public OrderController(OrderRepository orderRep, UserRepository userRep,
                           RoomReservationRepository roomResrvationRep) {
        this.orderRepository = orderRep;
        this.userRepository = userRep;
        this.roomReservationRepository = roomResrvationRep;
    }

    /**
     * Given an orderRequestModel it will save an order in the database.
     *
     * @param rs OrderRequestModel for order to be added
     * @return Order added
     */

    @PostMapping(value = "/createOrder", consumes = {"application/json"})
    public Order createNewOrder(@Valid @RequestBody OrderRequestModel rs) {
        double quantity = rs.getQuantity();
        FoodMenu foodMenu = rs.getFoodMenu();
        String username = rs.getUsername();
        long resId = rs.getResId();

        User user = userRepository.getByUserName(username);
        RoomReservation reservation = roomReservationRepository.getById(resId);

        Order order = new Order(user, quantity, foodMenu, reservation);
        return orderRepository.save(order);
    }

    /**
     * Get Endpoint to get orders.
     *
     * @return the orders.
     */
    @GetMapping("orders/{userName}")
    public Object sendOrders(@PathVariable String userName) {
        User user = userRepository.getByUserName(userName);
        List<Order> orders = orderRepository.findByUserId(user.getId());
        List<OrderRequestModel> orders2 = new ArrayList<>();

        for (Order u : orders) {
            OrderRequestModel req = new OrderRequestModel(u.getQuantity(),
                    u.getFoodMenu(), u.getUser().getUserName(), u.getReservation().getId());
            orders2.add(req);
        }

        return orders2;
    }

}
