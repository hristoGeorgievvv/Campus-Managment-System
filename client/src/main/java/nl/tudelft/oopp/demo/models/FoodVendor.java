package nl.tudelft.oopp.demo.models;

import java.util.List;
import nl.tudelft.oopp.demo.controllers.FoodController;

public class FoodVendor {

    /**
     * id of vendor.
     */
    private long id;

    /**
     * name of Vendor.
     */
    private String vendorName;

    /**
     * no arg constructor.
     */
    public FoodVendor() {
    }

    /**
     * constructor with id.
     * @param id id of vendor
     * @param vendorName name of vendor
     */
    public FoodVendor(long id, String vendorName) {
        this.id = id;
        this.vendorName = vendorName;
    }

    /**
     * constructor w/out id.
     * @param vendorName name of vendor
     */
    public FoodVendor(String vendorName) {
        this.vendorName = vendorName;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return vendorName;
    }

    public void setName(String name) {
        this.vendorName = name;
    }

    public List<FoodMenu> getMenuItems(FoodVendor vendor) {
        return FoodController.getFoodMenus(vendor);
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
        if (other instanceof FoodVendor) {
            FoodVendor that = (FoodVendor) other;
            return that.vendorName.equals(this.vendorName);
        }
        return false;
    }

}
