package nl.tudelft.oopp.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "foodMenu")
public class FoodMenu extends AbstractModel {
    /**
     * Name of food.
     */
    @NotBlank
    @Size(min = 3, max = 100)
    private String foodName;


    /**
     * Category of the food.
     */
    @NotBlank
    @Size(min = 3, max = 100)
    private String category;

    /**
     * Price of food.
     */
    @NotNull
    private double price;

    /**
     * Building the room is in.
     */
    @ManyToOne
    @JoinColumn(name = "foodVendor")
    private FoodVendor foodVendor;

    @OneToMany(mappedBy = "foodMenuO")
    private List<Order> orderList = new ArrayList<>();

    /**
     * No arg constructor.
     */
    public FoodMenu() {

    }

    /**
     * Main constructor.
     * @param foodName name of food
     * @param category type of food
     * @param price price of food
     * @param foodVendor store
     */
    public FoodMenu(String foodName, String category, double price, FoodVendor foodVendor) {
        this.category = category;
        this.foodName = foodName;
        this.foodVendor = foodVendor;
        this.price = price;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public FoodVendor getFoodVendor() {
        return foodVendor;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setFoodVendor(FoodVendor vendor) {
        this.foodVendor = vendor;
    }

    /**
     * Equals method for FoodMenu.
     * @param other the object we are comparing to
     * @return      true if other is equal, false otherwise
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

            if (foodName == null && category == null && foodVendor == null
                    && that.foodName == null && that.category == null && that.foodVendor == null) {
                return true;
            }

            return getFoodName().equals(that.getFoodName()) && getCategory().equals(
                    that.getCategory()) && getPrice() == that.getPrice()
                    && getFoodVendor().equals(that.getFoodVendor());
        }

        return false;
    }
}
