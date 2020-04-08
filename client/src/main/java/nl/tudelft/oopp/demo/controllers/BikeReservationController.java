package nl.tudelft.oopp.demo.controllers;

import com.google.gson.Gson;
import java.net.http.HttpResponse;
import nl.tudelft.oopp.demo.MainApp;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.models.BikeReservationModel;


public class BikeReservationController {

    /**
     * Return whether the user has bike reservation or not.
     *
     * @return boolean
     */

    public static boolean userHasBike() {

        HttpResponse<String> response = ServerCommunication.getUtility(
                "bikeReservation/" + MainApp.getUserName());
        if (response == null || response.statusCode() != 200) {
            return false;
        }

        boolean userHasBike = new Gson().fromJson(response.body(), boolean.class);

        return userHasBike;
    }

    /**
     * Updates the status of user bike reservation.
     *
     * @return boolean
     */

    public static boolean makeOrDeleteReservation(String buildingName) {
        BikeReservationModel input = new BikeReservationModel(MainApp.getUserName(), buildingName);
        HttpResponse<String> response = ServerCommunication.postUtility(
                "createBikeReservation", input);
        return response != null && response.statusCode() == 200;
    }



}
