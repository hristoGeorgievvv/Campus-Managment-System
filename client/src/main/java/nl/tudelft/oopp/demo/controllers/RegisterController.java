package nl.tudelft.oopp.demo.controllers;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import nl.tudelft.oopp.demo.models.User;

public class RegisterController {

    private static HttpClient client = HttpClient.newBuilder().build();

    /**
     * A copy of the getUtility method from serverCommunication without the header, as
     * the user will never be signed in while in the register screen.
     * @param mapping The mapping we send our request to
     * @return The response from the server
     */
    public static HttpResponse<String> getUtility(String mapping) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/" + mapping)).build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return response;
    }

    /**
     * Sends a request to the server which returns a boolean if the user is present in the database.
     * @param userName the Username we want to check if it is present in the database
     * @return true if the username is present, false otherwise
     */
    public static boolean exists(String userName) {
        HttpResponse<String> response = getUtility("users/exists/" + userName);
        if (response == null || response.statusCode() != 200) {
            return false;
        }
        if (response.body().equals("true")) {
            return true;
        }
        return false;
    }

    /**
     * Adds a user to the database with the credentials supplied.
     * @param userName the username of the new user
     * @param password the password of the new user
     * @param email the email of the new user
     * @param type the type of user the new user is (Student, teacher, etc)
     * @return true if the user was successfully added to database
     */
    public static boolean addUser(String userName, String password,
                                  String email, String type, boolean bike) {
        User user = new User(type,email,password,userName,false);

        Gson test = new Gson();

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(test.toJson(user)))
                .uri(URI.create("http://localhost:8080/users/add")).header("Content-Type", "application/json").build();
        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
            return false;
        }
        if (response == null) {
            return false;
        }
        if (response.body().equals("true")) {
            return true;
        }
        return false;
    }

}
