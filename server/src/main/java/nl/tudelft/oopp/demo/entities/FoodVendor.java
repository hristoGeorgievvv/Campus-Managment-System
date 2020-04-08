package nl.tudelft.oopp.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "FoodVendor")
public class FoodVendor extends AbstractModel {

    public FoodVendor() {
    }

    @Size(min = 3, max = 100)
    private String vendorName;

    public FoodVendor(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    /**
     * Equals method for FoodVendor.
     * @param other The object we are comparing to
     * @return      true if other is equal to this object, false otherwise
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
            if (vendorName == null && that.vendorName == null) {
                return true;
            } else if (vendorName == null || that.vendorName == null) {
                return false;
            }

            return vendorName.equals(that.vendorName);
        }

        return false;
    }
}
