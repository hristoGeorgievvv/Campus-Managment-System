package nl.tudelft.oopp.demo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import nl.tudelft.oopp.demo.repositories.FoodMenuRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class FoodMenuTest {

    @Autowired
    private FoodMenuRepository menuRep;

    private FoodVendor vendor = new FoodVendor();

    FoodMenu menu1 = new FoodMenu(
            "testFood",
            "testCategory",
            5.00,
            vendor
    );

    FoodMenu menu2 = new FoodMenu(
            "testFood",
            "testCategory",
            5.00,
            vendor
    );

    /**
     * testing.
     */

    @Test
    public void constructorTestEmpty() {
        FoodMenu menu = new FoodMenu();
        assertNotNull(menu, "Expected not null");
    }

    @Test
    public void constructorTest() {
        assertNotNull(menu1, "Expected not null");
        assertEquals("testFood", menu1.getFoodName(), "Food not properly saved");
        assertEquals("testCategory", menu1.getCategory(), "Category not properly saved");
        assertEquals(5.00, menu1.getPrice(), "Price not properly saved");
        assertEquals(vendor, menu1.getFoodVendor(), "Vendor not properly saved");
    }

    @Test
    public void getIdTest() {
        menuRep.save(menu1);
        assertNotNull(menu1.getId(), "Id not generated");
    }

    @Test
    public void setIdTest() {
        menuRep.save(menu1);
        menu1.setId(123L);
        assertEquals(123L, menu1.getId(), "setID is not functioning");
    }

    @Test
    public void getFoodNameTest() {
        assertEquals("testFood", menu1.getFoodName(), "GetFood not correct");
    }

    @Test
    public void getCategoryTest() {
        assertEquals("testCategory", menu1.getCategory(), "getCategory not correct");
    }

    @Test
    public void getPriceTest() {
        assertEquals(5.00, menu1.getPrice(), "getPrice not correct");
    }

    @Test
    public void getVendorTest() {
        assertEquals(vendor, menu1.getFoodVendor(), "getVendor not correct");
    }

    @Test
    public void setFoodNameTest() {
        menu1.setFoodName("Diff");
        assertEquals("Diff", menu1.getFoodName(), "FoodName setter not correct");
    }

    @Test
    public void setCategoryTest() {
        menu1.setCategory("Diff");
        assertEquals("Diff", menu1.getCategory(), "Category setter not correct");
    }

    @Test
    public void setPriceTest() {
        menu1.setPrice(10.00);
        assertEquals(10.00, menu1.getPrice(), "Price setter not correct");
    }

    @Test
    public void setVendorTest() {
        FoodVendor newVendor = new FoodVendor("newVendor");
        menu1.setFoodVendor(newVendor);
        assertEquals(newVendor, menu1.getFoodVendor(), "Vendor setter not correct");
    }

    /**
     * Equals testing.
     */

    @Test
    public void equalsAttributesTest() {
        assertTrue(menu1.equals(menu2), "Menus with the same attributes should be equal");
    }

    @Test
    public void equalsSameTest() {
        assertTrue(menu1.equals(menu1), "A menu should be equal to itself");
    }

    @Test
    public void equalsTestNotEqualFood() {
        menu2.setFoodName("diff");
        assertFalse(menu1.equals(menu2), "Menus with different foodNames should be different");
    }

    @Test
    public void equalsTestNotEqualCategory() {
        menu2.setCategory("diff");
        assertFalse(menu1.equals(menu2), "Menus with different Categories should be different");
    }

    @Test
    public void equalsTestNotEqualPrice() {
        menu2.setPrice(100.00);
        assertFalse(menu1.equals(menu2), "Menus with different Prices should be different");
    }

    @Test
    public void equalsTestNotEqualVendor() {
        FoodVendor newVendor = new FoodVendor("newVendor");
        menu2.setFoodVendor(newVendor);
        assertFalse(menu1.equals(menu2), "Menus with different Vendors should be different");
    }

    @Test
    public void equalsTestNoConstructor() {
        FoodMenu m1 = new FoodMenu();
        FoodMenu m2 = new FoodMenu();
        assertTrue(m1.equals(m2), "Menus with empty constructors should be equal");
    }

}
