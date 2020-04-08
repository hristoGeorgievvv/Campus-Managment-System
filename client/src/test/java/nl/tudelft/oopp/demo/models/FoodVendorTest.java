package nl.tudelft.oopp.demo.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FoodVendorTest {
    private FoodVendor testFoodVendor = new FoodVendor(
            123L,
            "testName"
    );


    //Tests start here
    @Test
    public void foodVendorConstructorTestEmptyConstructor() {
        FoodVendor testFoodVendor = new FoodVendor();
        assertNotNull(testFoodVendor, "Expected not null");
    }

    @Test
    public void foodVendorConstructorTest() {
        assertNotNull(testFoodVendor, "Expected not null");
        assertEquals(123L, testFoodVendor.getId(), "ID saved incorrectly");
        assertEquals("testName", testFoodVendor.getName(), "VendorName saved incorrectly");
    }

    @Test
    public void foodVendorConstructorTestNoId() {
        FoodVendor testFoodVendor = new FoodVendor(
                "testName"
        );
        assertNotNull(testFoodVendor, "Expected not null");
        assertEquals("testName", testFoodVendor.getName(), "Vendor name saved incorrectly");
    }

    @Test
    public void getidTest() {
        assertEquals(123L, testFoodVendor.getId(), "getID not correct");
    }

    @Test
    public void getVendorNameTest() {
        assertEquals("testName", testFoodVendor.getName(), "getVendorName not correct");
    }

    @Test
    public void setNameTest() {
        testFoodVendor.setName("newName");
        assertEquals("newName", testFoodVendor.getName(), "setName not correct");
    }

    @Test
    public void getMenuItemsTest() {
        assertNull(testFoodVendor.getMenuItems(testFoodVendor));
    }

    //Equals testing
    @Test
    public void equalsTest() {
        FoodVendor testFoodVendor2 = new FoodVendor(
                123L,
                "testName"
        );
        assertTrue(testFoodVendor.equals(testFoodVendor2),
                "FoodVendors with same name should be equal");
    }

    @Test
    public void equalsTestSame() {
        assertTrue(testFoodVendor.equals(testFoodVendor), "same FoodVendors should be equal");
    }

    @Test
    public void equalsTestNotEqual() {
        FoodVendor testFoodVendor2 = new FoodVendor(
                123L,
                "testName2"
        );
        assertFalse(testFoodVendor.equals(testFoodVendor2),
                "FoodVendors with different names should not be equal");
    }

}