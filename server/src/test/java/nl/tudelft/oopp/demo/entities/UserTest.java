package nl.tudelft.oopp.demo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class UserTest {

    @Autowired
    private UserRepository userRep;

    private User user = new User(
            "testName",
            "testType",
            "testEmail",
            "testPword",false
    );

    private User user2 = new User(
            "testName",
            "testType",
            "testEmail",
            "testPword",
            false
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
        assertEquals("testName", user.getUserName(), "Username not properly saved");
        assertEquals("testType", user.getType(), "Type not properly saved");
        assertEquals("testEmail", user.getEmail(), "Email not properly saved");
        assertEquals("testPword", user.getPassword(), "Password not properly saved");
    }

    @Test
    public void getIdTest() {
        userRep.save(user);
        assertNotNull(user.getId(), "Id not generated");
    }

    @Test
    public void setIdTest() {
        userRep.save(user);
        user.setId(123L);
        assertEquals(123L, user.getId(), "setID is not functioning");
    }

    @Test
    public void getUserNameTest() {
        assertEquals("testName", user.getUserName(), "getUserName not correct");
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
        assertFalse(user.getBike(), "User should not have a bike");
    }

    @Test
    public void setUserNameTest() {
        user.setUserName("testName2");
        assertEquals("testName2", user.getUserName(), "setUserName not correct");
    }

    @Test
    public void setBikeTest() {
        user.setBike(true);
        assertTrue(user.getBike(), "User should have a bike");
    }

    @Test
    public void setTypeTest() {
        user.setType("testType2");
        assertEquals("testType2", user.getType(), "setType not correct");
    }

    @Test
    public void setEmailTest() {
        user.setEmail("testEmail2");
        assertEquals("testEmail2", user.getEmail(), "setEmail not correct");
    }

    @Test
    public void setPasswordTest() {
        user.setPassword("testPword2");
        assertEquals("testPword2", user.getPassword(), "setPassword not correct");
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
        user2.setUserName("testName2");
        assertFalse(user.equals(user2), "users with different userNames should not be equal");
        user2.setUserName("testName");
        user2.setEmail("DifferentEmail");
        assertFalse(user.equals(user2), "users with different emails should not be equal");
        user2.setEmail("testEmail");
        user2.setType("Diff");
        assertFalse(user.equals(user2), "users with different types should not be equal");
        user2.setType("testType");
        user2.setPassword("Diff");
        assertFalse(user.equals(user2), "users with different passwords should not be equal");
        user2.setPassword("testPword");
        user2.setBike(true);
        assertFalse(user.equals(user2),
                "users with different bike rent status should not be equal");
    }

    @Test
    public void equalsTestEqual() {
        assertTrue(user.equals(user2), "users with same attributes should be equal");
        user2.setEmail("Diff");
        user.setEmail("Diff");
        assertTrue(user.equals(user2), "users with same attributes should be equal");
    }
}
