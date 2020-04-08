package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;


public class RegisterControllerTest {

    @Test
    public void existsTest() {
        assertFalse(RegisterController.exists("testNameTesting"),
                "Expected false, with invalid http response");
    }

    @Test
    public void getUtilityTest() {
        assertNull(RegisterController.getUtility("fakeTestMapping"),
                "Expected false, with invalid http response");
    }
}