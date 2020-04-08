package nl.tudelft.oopp.demo.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import nl.tudelft.oopp.demo.MainApp;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.models.User;

public class LoginController {

    /**
     * Returns a list with the names of all the users in the database.
     * @return a list of strings with the names of all users in the database
     */
    public static List<String> getAllUsers() {
        HttpResponse<String> response = ServerCommunication.getUtility("users/all");
        if (response == null || response.statusCode() != 200) {
            return null;
        }

        Type listType = new TypeToken<ArrayList<User>>(){}.getType();
        List<User> users = new Gson().fromJson(response.body(), listType);

        List<String> userNames = new ArrayList<>();

        for (User u: users) {
            userNames.add(u.getUsername());
        }

        return userNames;

    }

    /**
     * Checks with the database if the username and password combination is present in the database.
     * @param username A string containing the username of the user we want to find
     * @param password A string with the password of the user we want to find
     * @return a boolean returning true if the username and password combination is in the database
     */
    public static boolean login(String username, String password) {
        HttpResponse<String> response = ServerCommunication.getUtility("users/login/"
                + username + "/" + password, username, password);
        if (response == null || response.statusCode() != 200) {
            return false;
        }
        if (response.body().equals("true")) {
            MainApp.setHeaderDetails(username, password);
            return true;
        }
        return false;
    }

}
