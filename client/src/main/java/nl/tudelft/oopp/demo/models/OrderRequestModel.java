package nl.tudelft.oopp.demo.models;

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

    public long getResId() {
        return resId;
    }
}
