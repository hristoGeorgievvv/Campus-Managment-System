package nl.tudelft.oopp.demo.models;

import java.util.ArrayList;
import java.util.List;

public class MainOrder {
    /**
     * The user that holds this order.
     */
    private String userName;

    private List<Order> orders = new ArrayList<>();

    public MainOrder(String userName) {
        this.userName = userName;
    }

    public String getUser() {
        return userName;
    }

    public void setUser(String userName) {
        this.userName = userName;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    /** If other is equal will return true if not false.
     * @param other object to compare
     * @return whether or not object is equal to this
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (other instanceof MainOrder) {
            MainOrder that = (MainOrder) other;
            return this.userName.equals(that.userName) && this.orders.equals(that.orders);
        }
        return false;
    }
}
