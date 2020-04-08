package nl.tudelft.oopp.demo.models;

public class Order {
    /**
     * The MainOrder that this order maps to.
     */
    private MainOrder mainOrder;

    /**
     * Quantity of food.
     */
    private double quantity;

    /**
     * The MainOrder that this order maps to.
     */
    private FoodMenu foodMenu;


    /**
     * The name of the User that made the order.
     */
    private String username;

    /**
     * The reservation for which the order has been made.
     */
    private long resId;

    /**
     * constructor for an order.
     * @param mainOrder user
     * @param quantity quantity
     * @param foodMenu food item
     */
    public Order(MainOrder mainOrder, double quantity, FoodMenu foodMenu,
                 String username, long resID) {
        this.mainOrder = mainOrder;
        this.quantity = quantity;
        this.foodMenu = foodMenu;
        this.username = username;
        this.resId = resID;
    }

    public long getResId() {
        return this.resId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order order = (Order) o;

        return foodMenu != null ? foodMenu.equals(order.foodMenu) : order.foodMenu == null;
    }



}
