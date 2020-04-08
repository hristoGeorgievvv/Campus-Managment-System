package nl.tudelft.oopp.demo.communication;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import nl.tudelft.oopp.demo.MainApp;


public class ServerCommunication {

    private static HttpClient client = HttpClient.newBuilder().build();

    /**
     * Retrieves a quote from the server.
     * @return the body of a get request to the server.
     * @throws Exception if communication with the server fails.
     */

    public static HttpResponse<String> getUtility(String mapping) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/" + mapping)).setHeader("Authorization", "Basic " + MainApp.getHeaderDetails()).build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (response.statusCode() == 401) {
            return null;
        } else if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return response;
    }

    /**
     * Retrives a response from the server. This is used only when logging in for the first time.
     * @param mapping The mapping we send our get request to
     * @param username username of the user
     * @param password password of the user
     * @return the response from the server
     */
    public static HttpResponse<String> getUtility(String mapping,
                                                  String username, String password) {
        String userDetails = username + ":" + password;
        String encodedDetails = Base64.getEncoder().encodeToString(userDetails.getBytes());
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/" + mapping)).setHeader("Authorization", "Basic " + encodedDetails).build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
            return null;
        }
        return response;
    }

    /**
     * this method should have a proper javadoc.
     * @param mapping The mapping name of the location
     * @return HTTP response from the server
     */
    public static HttpResponse<String> deleteUtility(String mapping) {
        HttpRequest request = HttpRequest.newBuilder().DELETE().uri(URI.create("http://localhost:8080/" + mapping)).setHeader("Authorization", "Basic " + MainApp.getHeaderDetails()).build();
        HttpResponse<String> response = null;
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
     * utility for postmapping.
     * @param mapping the mapping of the request
     * @param object The object we will convert into json
     * @return an HTTP response from the server
     */
    public static HttpResponse<String> postUtility(String mapping,Object object) {
        Gson gson = new Gson();
        String obj = gson.toJson(object);
        HttpRequest request = HttpRequest.newBuilder().POST(
                HttpRequest.BodyPublishers.ofString(obj)
        ).uri(URI.create("http://localhost:8080/" + mapping))
                .header("Content-Type","application/json")
                .setHeader("Authorization", "Basic " + MainApp.getHeaderDetails())
                .build();
        HttpResponse<String> response = null;
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
     * Returns a string with the classrooms in the building.
     * @param building A string with the name of the building we want to find the classrooms in.
     * @return a string with the names of the classrooms from the database.
     */
    public static String getClassrooms(String building) {
        HttpResponse<String> response = getUtility("classroom/byBuilding/" + building);
        if (response == null) {
            return "Communication with server failed";
        }
        return response.body();
    }

    /**
     * Update using the postmapping.
     * @param mapping the mapping of the request
     * @param object the object we want to update
     * @return an HTTP response from the server
     */
    public static HttpResponse<String> updateUtility(String mapping, Object object) {
        Gson gson = new Gson();
        String obj = gson.toJson(object);
        HttpRequest request = HttpRequest.newBuilder().PUT(
                HttpRequest.BodyPublishers.ofString(obj)
        ).uri(URI.create("http://localhost:8080/" + mapping))
                .header("Content-Type","application/json")
                .setHeader("Authorization", "Basic " + MainApp.getHeaderDetails())
                .build();
        HttpResponse<String> response = null;
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


}
