package nl.tudelft.oopp.demo.services;

import nl.tudelft.oopp.demo.entities.FoodMenu;

public class OrderRequestModel {

    /**
     * Quantity of food.
     */
    private double quantity;

    /**
     * The MainOrder that this order maps to.
     */
    private FoodMenu foodMenu;

    private String username;

    private long resId;

    /**
     * constructor for an order.
     * @param quantity quantity
     * @param foodMenu food item
     * @param username username of user
     */
    public OrderRequestModel(double quantity, FoodMenu foodMenu, String username, long resId) {
        this.quantity = quantity;
        this.foodMenu = foodMenu;
        this.username = username;
        this.resId = resId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public FoodMenu getFoodMenu() {
        return foodMenu;
    }

    public void setFoodMenu(FoodMenu foodMenu) {
        this.foodMenu = foodMenu;
    }

    public String getUsername() {
        return this.username;
    }

    public long getResId() {
        return resId;
    }

    /**
     * Equals method for OrderRequestModel.
     * @param other object to compare to
     * @return true if the other object has equal attributes, false otherwise
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other == this) {
            return true;
        }
        if (other instanceof OrderRequestModel) {
            OrderRequestModel that = (OrderRequestModel) other;
            return (this.foodMenu.equals(that.foodMenu) && this.quantity == that.quantity
                    && this.resId == that.resId && this.username.equals(that.username));
        }
        return false;
    }
}
