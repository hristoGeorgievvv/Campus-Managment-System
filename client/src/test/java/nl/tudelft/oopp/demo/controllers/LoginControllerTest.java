package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;


public class LoginControllerTest {

    @Test
    public void getAllUsersTest() {
        assertNull(LoginController.getAllUsers(), "Expected null, with invalid http response");
    }

    @Test
    public void loginTest() {
        assertFalse(LoginController.login("invalidusernamefortests", "invalidpasswordfortests"),
                "Expected null, with invalid http response");
    }
}
