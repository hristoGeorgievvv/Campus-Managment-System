package nl.tudelft.oopp.demo.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FoodMenuTest {

    private FoodVendor testFoodVendor = new FoodVendor("testName");

    private FoodMenu testFoodMenu = new FoodMenu(
            123L,
            "testName",
            "testCategory",
            123,
            testFoodVendor
    );

    //Tests start here
    @Test
    public void foodMenuConstructorTest() {
        assertNotNull(testFoodMenu, "Expected not null");
        assertEquals("testCategory", testFoodMenu.getCategory(), "category saved incorrectly");
        assertEquals("testName", testFoodMenu.getFoodName(), "FoodName saved incorrectly");
        assertEquals(123, testFoodMenu.getPrice(), "Price saved incorrectly");
        assertEquals(testFoodVendor, testFoodMenu.getVendor(), "Vendor saved incorrectly");
    }

    @Test
    public void getCategoryTest() {
        assertEquals("testCategory", testFoodMenu.getCategory(),
                "getCategory incorrect");
    }

    @Test
    public void getFoodNameTest() {
        assertEquals("testName", testFoodMenu.getFoodName(), "getFoodName incorrect");
    }

    @Test
    public void getPriceTest() {
        assertEquals(123, testFoodMenu.getPrice(), "getPrice incorrect");
    }

    @Test
    public void getVendorTest() {
        assertEquals(testFoodVendor, testFoodMenu.getVendor(), "getVendor incorrect");
    }

    @Test
    public void setCategoryTest() {
        testFoodMenu.setCategory("newCategory");
        assertEquals("newCategory", testFoodMenu.getCategory(), "setCategory incorrect");
    }

    @Test
    public void setFoodNameTest() {
        testFoodMenu.setFoodName("newName");
        assertEquals("newName", testFoodMenu.getFoodName(), "setFoodName incorrect");
    }

    @Test
    public void setPriceTest() {
        testFoodMenu.setPrice(321);
        assertEquals(321, testFoodMenu.getPrice(), "setPrice incorrect");
    }

    @Test
    public void setVendorTest() {
        FoodVendor testFoodVendor2 = new FoodVendor("vendorName2");
        testFoodMenu.setVendor(testFoodVendor2);
        assertEquals(testFoodVendor2, testFoodMenu.getVendor(), "setVendor incorrect");
    }

    //Equals testing
    @Test
    public void equalsTest() {
        FoodMenu testFoodMenu2 = new FoodMenu(
                123L,
                "testName",
                "testCategory",
                123,
                testFoodVendor
        );
        assertTrue(testFoodMenu.equals(testFoodMenu2),
                "A foodMenu should be equal if they have same attributes");
    }

    @Test
    public void equalsTestSame() {
        assertTrue(testFoodMenu.equals(testFoodMenu), "Same foodMenus should be equal");
    }

    @Test
    public void equalsTestNotEqual() {
        FoodVendor testFoodVendor2 = new FoodVendor("vendorName2");
        FoodMenu testFoodMenu2 = new FoodMenu(
                1234L,
                "testName2",
                "testCategory",
                321,
                testFoodVendor2
        );
        assertFalse(testFoodMenu.equals(testFoodMenu2), "Different FoodMenus should not be equal");
    }

}