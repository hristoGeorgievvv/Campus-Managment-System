package nl.tudelft.oopp.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/unauthenticated")
    public static String something() {
        return "You are unauthenticated";
    }
}
