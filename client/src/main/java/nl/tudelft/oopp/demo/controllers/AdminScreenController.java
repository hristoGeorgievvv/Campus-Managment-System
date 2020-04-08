package nl.tudelft.oopp.demo.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.models.User;

public class AdminScreenController {

    /**
     * Returns a list with the names of all the users in the database.
     * @return a list of strings with the names of all users in the database
     */
    public static String getUserType(String username) {
        HttpResponse<String> response = ServerCommunication.getUtility("users/all");
        if (response == null || response.statusCode() != 200) {
            return null;
        }

        Type listType = new TypeToken<ArrayList<User>>(){}.getType();
        List<User> users = new Gson().fromJson(response.body(), listType);

        String type = "null";

        for (User u: users) {
            if (u.getUsername().equals(username)) {
                type = u.getType();
            }
        }
        return type;
    }

    /**
     * Updates a building.
     * @param user Name of the building we want to update
     * @return true if we get a response from the server, false otherwise
     */
    public static boolean updateType(User user) {
        HttpResponse<String> status;
        status = ServerCommunication.updateUtility("users/updateUserType", user);
        if (status == null || status.statusCode() != 200) {
            return false;
        }
        return true;
    }

}
