package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class LoginControllerTest {

    @Test
    public void somethingTest() {
        assertEquals("You are unauthenticated", LoginController.something(),
                "something doesnt work!");
    }
}
