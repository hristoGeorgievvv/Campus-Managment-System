package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import nl.tudelft.oopp.demo.models.User;
import org.junit.jupiter.api.Test;


public class AdminScreenControllerTest {

    private User testUser = new User(
            "testType",
            "testEmail",
            "testPword",
            "testName",
            false
    );

    @Test
    public void getUserTypeTest() {
        assertNull(AdminScreenController.getUserType("TestingNameInvalid"),
                "Expected null, with invalid http response");
    }

    @Test
    public void updateTypeTest() {
        assertFalse(AdminScreenController.updateType(testUser),
                "Expected null, with invalid http response");
    }
}
