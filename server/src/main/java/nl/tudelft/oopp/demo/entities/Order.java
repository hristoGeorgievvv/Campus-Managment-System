package nl.tudelft.oopp.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity // This tells Hibernate to make a table out of this class
@Table(name = "orders")
public class Order extends AbstractModel {
    public Order() {
    }

    /**
     * Quantity of food.
     */
    private double quantity;

    /**
     * The MainOrder that this order maps to.
     */
    @ManyToOne
    @JoinColumn(name = "foodMenu_id", referencedColumnName = "id")
    private FoodMenu foodMenuO;

    @ManyToOne
    @JsonIgnore
    @JoinColumn()
    private User user;

    @ManyToOne
    @JoinColumn()
    @JsonIgnore
    private RoomReservation reservation;

    /**
     * Constructor for order.
     * @param user user that made the order
     * @param quantity number of items
     * @param foodMenu the food item
     */
    public Order(User user, double quantity, FoodMenu foodMenu, RoomReservation reservation) {
        this.quantity = quantity;
        this.user = user;
        this.foodMenuO = foodMenu;
        this.reservation = reservation;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public FoodMenu getFoodMenu() {
        return this.foodMenuO;
    }

    public User getUser() {
        return this.user;
    }

    public RoomReservation getReservation() {
        return reservation;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setFoodMenu(FoodMenu foodMenuO) {
        this.foodMenuO = foodMenuO;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setReservation(RoomReservation reservation) {
        this.reservation = reservation;
    }

    /**
     * equals method for class Order.
     * @param other the object to compare to
     * @return true if other has all the same attributes as this. False otherwise
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other == this) {
            return true;
        }

        if (other instanceof Order) {
            Order that = (Order) other;
            if (this.foodMenuO == null && this.user == null && this.reservation == null
                    && that.foodMenuO == null && that.user == null && that.reservation == null) {
                return true;
            }
            return (this.foodMenuO.equals(that.foodMenuO) && this.user.equals(that.user) && this
                    .reservation.equals(that.reservation) && this.quantity == that.quantity);
        }

        return false;
    }
}
