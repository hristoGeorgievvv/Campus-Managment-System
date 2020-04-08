package nl.tudelft.oopp.demo;

import java.util.Base64;
import java.util.Scanner;

import nl.tudelft.oopp.demo.models.MainOrder;
import nl.tudelft.oopp.demo.views.LoginScreen;

public class MainApp {

    private static String headerDetails;

    private static MainOrder mainOrder = new MainOrder(getUserName());

    /**
     * Executes the main application starting from the login screen.
     * @param args arguments
     */

    public static void main(String[] args) {
        LoginScreen.execute(args);

    }

    public static void logout() {
        headerDetails = null;
    }

    public static MainOrder getMainOrder() {
        return mainOrder;
    }

    /**
     * Sets encrypted details for the header for the authentication.
     * @param username the username of the user
     * @param password the unencrypted password of the user
     */
    public static void setHeaderDetails(String username, String password) {
        String userDetails = username + ":" + password;
        String encodedDetails = Base64.getEncoder().encodeToString(userDetails.getBytes());
        headerDetails = encodedDetails;
    }

    public static String getHeaderDetails() {
        return headerDetails;
    }

    /**
     * Gets user name of the currently authenticated user from headerdetails.
     * @return username of the user
     */
    public static String getUserName() {
        if (headerDetails == null) {
            return null;
        }
        byte[] byteArray = Base64.getDecoder().decode(headerDetails);
        String userDetails = new String(byteArray);
        Scanner scan = new Scanner(userDetails).useDelimiter(":");
        String username = scan.next();
        return username;
    }

    public static void resetMainOrder() {
        mainOrder = new MainOrder(getUserName());
    }

}
