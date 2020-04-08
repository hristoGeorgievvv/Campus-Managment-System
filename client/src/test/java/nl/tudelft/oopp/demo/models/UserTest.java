package nl.tudelft.oopp.demo.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import nl.tudelft.oopp.demo.models.User;
import org.junit.jupiter.api.Test;

public class UserTest {
    private User user = new User(
            "testType",
            "testEmail",
            "testPword",
            "testName",false
    );

    private User user2 = new User(
            "testType",
            "testEmail",
            "testPword",
            "testName",false
    );

    /**
     * testing.
     */

    @Test
    public void constructorTestEmpty() {
        User user = new User();
        assertNotNull(user, "Expected not null");
    }

    @Test
    public void constructorTest() {
        assertNotNull(user, "Expected not null");
        assertEquals("testName", user.getUsername(), "Username not properly saved");
        assertEquals("testType", user.getType(), "Type not properly saved");
        assertEquals("testEmail", user.getEmail(), "Email not properly saved");
        assertEquals("testPword", user.getPassword(), "Password not properly saved");
        assertEquals(false, user.getBike(), "Bike boolean not properly saved");
    }


    @Test
    public void getUserNameTest() {
        assertEquals("testName", user.getUsername(), "getUserName not correct");
    }

    @Test
    public void getTypeTest() {
        assertEquals("testType", user.getType(), "getType not correct");
    }

    @Test
    public void getEmailTest() {
        assertEquals("testEmail", user.getEmail(), "getEmail not correct");
    }

    @Test
    public void getPasswordTest() {
        assertEquals("testPword", user.getPassword(), "getPassword not correct");
    }

    @Test
    public void getBikeTest() {
        assertEquals(false, user.getBike(), "getBike not correct");
    }

    /**
     * Equals testing.
     */

    @Test
    public void equalsTest() {
        assertTrue(user.equals(user2), "equal users should be equal");
    }

    @Test
    public void equalsTestSame() {
        assertTrue(user.equals(user), "same user should be equal");
    }

    @Test
    public void equalsTestNotEqual() {
        User user2 = new User(
                "testType",
                "testEmail2",
                "testPword",
                "testName",
                false
        );
        assertFalse(user.equals(user2), "users with different emails should not be equal");
    }

    @Test
    public void equalsTestEqualEmailOnly() {
        User user2 = new User(
                "testType2",
                "testEmail",
                "testPword2",
                "testName2",
                true
        );
        assertTrue(user.equals(user2), "users with same emails should be equal");
    }
}
