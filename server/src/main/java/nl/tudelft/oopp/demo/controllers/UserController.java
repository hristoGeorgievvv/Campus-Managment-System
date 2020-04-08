package nl.tudelft.oopp.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import nl.tudelft.oopp.demo.entities.User;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Transactional
@RestController
@Controller
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository rep;

    @Autowired private BCryptPasswordEncoder encoder;

    //Constructor used in testing
    public UserController(UserRepository rep) {
        this.rep = rep;
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return rep.findAll();
    }

    /**
     * Uses postMapping to create a new user.
     * @param user takes in a user from the json file
     * @return A string saying that the user was saved to the database
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String addNewUser(@Valid @RequestBody User user) {
        User newUser = user;
        user.setPassword(encoder.encode(user.getPassword()));
        rep.save(newUser);
        return "Saved";
    }


    @GetMapping(path = "user/{name}")
    @ResponseBody
    public List<User> getUsers(@PathVariable String name) {
        return rep.findByUsername(name);
    }

    /**
     * checks if the username and password of this user match each other to authenticate a user.
     * @param name the userName of the user
     * @param pwrd the password of the user
     * @return true if the user is in the database and if the password matches, false otherwise
     */
    @GetMapping(path = "login/{name}/{pwrd}")
    @ResponseBody
    public boolean authenticate(@PathVariable (value = "name") String name,
                                @PathVariable (value = "pwrd") String pwrd,
                                HttpServletResponse response) {

        return true;
    }

    /**
     * simply finds if there is a user with the specified username is already in the database.
     * @param name userName we want to check
     * @return true if there is a user in the database with that userName
     */
    @GetMapping(path = "exists/{name}")
    @ResponseBody
    public boolean exists(@PathVariable String name) {
        User user = rep.getByUserName(name);
        return (user != null && user.getUserName().equals(name));
    }

    /**
     * adds a new user to the database using getMapping and the following input parameters.
     * @param username username of the new User
     * @param password password of the new user
     * @param email email of the new user
     * @param role type of user (Student, Teacher, etc)
     * @param bike if the user is renting a bike
     * @return true if the user was saved
     */
    @GetMapping(path = "addUser/{username}/{userpassword}/{email}/{role}/{bike}")
    @ResponseBody
    public boolean addNewUserToDB(@PathVariable(value = "username") String username,
                                  @PathVariable(value = "userpassword") String password,
                                  @PathVariable(value = "email") String email,
                                  @PathVariable(value = "role") String role,
                                  @PathVariable boolean bike) {

        User newUser = new User(role, email, password, username, bike);
        try {
            rep.save(newUser);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * returns a list of strings with one object, true or false depending
     * on if the user has a bike or not.
     * @param name name of the user
     * @return a list with one input: true if the user has a bike, false otherwise
     */
    @GetMapping("test/{name}")
    @ResponseBody
    public ArrayList<String> test(@PathVariable String name) {
        ArrayList<String> list = new ArrayList<>();
        list.add(0,rep.findTypeUsername(name));
        if (rep.hasBike(name)) {
            list.add(1,"true");
        } else {
            list.add(1,"false");
        }
        return list;
    }

    /**
     * gets user details from the database from a specific user.
     Details are in the order username, email, id, type.
     * @param name the name of the user we want to get details from
     * @return a list with the details from the user
     */
    @GetMapping("details/{name}")
    @ResponseBody
    public List<String> getUserDetails(@PathVariable String name) {
        List<String> res = new ArrayList<>();
        User user = rep.getByUserName(name);
        res.add(user.getUserName());
        res.add(user.getEmail());
        res.add(user.getId().toString());
        res.add(user.getType());
        return res;
    }

    @GetMapping("type/{name}")
    @ResponseBody
    public String getType(@PathVariable String name) {
        return rep.getByUserName(name).getType();
    }

    @GetMapping("users")
    @ResponseBody public Iterable<User> showAll() {
        return rep.findAll();
    }

    /**
     * updates the information in a building.
     * @param user The name of the building we want to update
     */
    @PutMapping(value = "/updateUserType", consumes = {"application/json"})
    public void updateType(@Valid @RequestBody User user) {
        rep.setUUser(user.getType(), user.getUserName());
        //System.out.println(building.getOpeningHours());
    }

}
