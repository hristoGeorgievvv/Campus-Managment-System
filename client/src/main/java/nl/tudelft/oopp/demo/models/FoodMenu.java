package nl.tudelft.oopp.demo.models;


public class FoodMenu {

    /**
     * id from database of foodMenu.
     */
    long id;
    /**
     * Name of food.
     */
    private String foodName;

    /**
     * Category of the food.
     */
    private String category;

    /**
     * Price of food.
     */
    private double price;

    /**
     * foodVendor Object.
     */
    private FoodVendor foodVendor;

    /**
     * FoodMenu constructor including id.
     * @param foodName name of food
     * @param category category
     * @param price price of food
     * @param foodVendor vendor that sells that food
     */
    public FoodMenu(long id, String foodName, String category,
                    double price, FoodVendor foodVendor) {
        this.id = id;
        this.category = category;
        this.foodName = foodName;
        this.price = price;
        this.foodVendor = foodVendor;
    }

    /**
     * FoodMenu constructor.
     * @param foodName name of food
     * @param category category
     * @param price price of food
     * @param foodVendor vendor that sells that food
     */
    public FoodMenu(String foodName, String category, double price, FoodVendor foodVendor) {
        this.category = category;
        this.foodName = foodName;
        this.price = price;
        this.foodVendor = foodVendor;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public FoodVendor getVendor() {
        return foodVendor;
    }

    public void setVendor(FoodVendor vendor) {
        this.foodVendor = vendor;
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
        if (other instanceof FoodMenu) {
            FoodMenu that = (FoodMenu) other;
            return this.id == that.id
                    || (this.foodName.equals(that.foodName)
                    && this.foodVendor.equals(that.foodVendor)
                    && this.price == that.price);
        }
        return false;
    }
}
