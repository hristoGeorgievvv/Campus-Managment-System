package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import nl.tudelft.oopp.demo.entities.User;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserControllerTest {

    @Autowired
    private UserRepository userRep;

    private User user = new User(
            "testName",
            "testType",
            "testEmail",
            "testPword",
            false
    );
    private User user2 = new User(
            "testName2",
            "testType2",
            "testEmail2",
            "testPword2",
            false
    );

    @Test
    public void getAllUsersTest() {
        userRep.save(user);
        userRep.save(user2);
        UserController myUC = new UserController(userRep);
        assertEquals(userRep.findAll(), myUC.getAllUsers(), "getAllUsers not functioning properly");
    }

    //    @Test
    //    public void addNewUsersTest() {
    //        UserController myUC = new UserController(userRep);
    //        assertEquals("Saved", myUC.addNewUser(user),
    //                "addNewUsers not return properly");
    //        assertEquals(user, userRep.findAll().iterator().next(), "User not properly saved");
    //    }

    @Test
    public void existsTest() {
        userRep.save(user);
        UserController myUC = new UserController(userRep);
        assertTrue(myUC.exists("testName"),
                "Exists not functioning properly not functioning properly");
    }


    @Test
    public void getDetailsTest() {
        userRep.save(user);
        List<String> expected = new ArrayList<String>();
        expected.add(user.getUserName());
        expected.add(user.getEmail());
        expected.add(user.getId().toString());
        expected.add(user.getType());
        UserController myUc = new UserController(userRep);
        assertEquals(expected, myUc.getUserDetails("testName"), "wrong list of details");
    }

    @Test
    public void getTypeTest() {
        userRep.save(user);
        UserController myUC = new UserController(userRep);
        assertEquals("testType", myUC.getType("testName"),
                "Exists not functioning properly not functioning properly");
    }

}
