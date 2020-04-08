package nl.tudelft.oopp.demo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class FoodVendorTest {

    FoodVendor vendor1 = new FoodVendor("TestVendor");

    FoodVendor vendor2 = new FoodVendor("TestVendor");

    /**
     * testing.
     */

    @Test
    public void constructorTestEmpty() {
        FoodVendor vendor = new FoodVendor();
        assertNotNull(vendor, "Expected not null");
    }

    @Test
    public void constructorTest() {
        assertNotNull(vendor1, "Expected not null");
        assertEquals("TestVendor", vendor1.getVendorName(), "Username not properly saved");
    }

    @Test
    public void getVendorNameTest() {
        assertEquals("TestVendor", vendor1.getVendorName(), "getVendorName not correct");
    }

    @Test
    public void setVendorNameTest() {
        vendor1.setVendorName("diff");
        assertEquals("diff", vendor1.getVendorName(), "SetVendorName not correct");
    }

    /**
     * Equals testing.
     */

    @Test
    public void equalsTest() {
        assertTrue(vendor1.equals(vendor2), "equal vendors should be equal");
    }

    @Test
    public void equalsTestSame() {
        assertTrue(vendor1.equals(vendor1), "same vendor should be equal");
    }

    @Test
    public void equalsTestNotSame() {
        vendor2.setVendorName("diff");
        assertFalse(vendor1.equals(vendor2),
                "vendors with different attributes should be different");
    }

    @Test
    public void equalsTestEmptyConstructor() {
        FoodVendor v1 = new FoodVendor();
        FoodVendor v2 = new FoodVendor();
        assertTrue(v1.equals(v2), "Vendors without a constructor should be equal");
    }

}
