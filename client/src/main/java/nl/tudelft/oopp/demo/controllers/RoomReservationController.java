package nl.tudelft.oopp.demo.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import nl.tudelft.oopp.demo.MainApp;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.models.ReservationRequestModel;
import nl.tudelft.oopp.demo.models.RoomReservation;

public class RoomReservationController {


    /**
     * gets a list of all room reservations.
     * @return returns a list of all room reservations made
     */
    public static List<RoomReservation> getReservations() {
        String mapping = "reservations/";
        HttpResponse<String> response = ServerCommunication.getUtility(mapping);

        if (response == null) {
            return null;
        }

        System.out.println(response.body());

        Type listType = new TypeToken<ArrayList<RoomReservation>>(){}.getType();
        List<RoomReservation> reservations = new Gson().fromJson(response.body(), listType);

        return reservations;
    }

    /**
     * Get the reservations for the current user. (Could be wrong, I am not the original author)
     * @return a list with the room reservations for the current user
     */
    public static List<RoomReservation> getReservationsPerUser() {
        String mapping = "reservations/" + MainApp.getUserName();
        HttpResponse<String> response = ServerCommunication.getUtility(mapping);
        if (response == null) {
            return new ArrayList<>();
        }


        Type listType = new TypeToken<ArrayList<RoomReservation>>(){}.getType();
        List<RoomReservation> reservations = new Gson().fromJson(response.body(), listType);

        return reservations;
    }

    /**
     * Deletes a reservation from a reservation request model layout.
     * @param request the reservation to delete
     * @return true if it deleted successfully, false otherwise
     */
    public static boolean deleteReservation(ReservationRequestModel request) {
        HttpResponse<String> response = ServerCommunication.postUtility(
                "deleteReservation", request);

        if (response.statusCode() != 200) {
            return false;
        }
        return true;
    }

}
