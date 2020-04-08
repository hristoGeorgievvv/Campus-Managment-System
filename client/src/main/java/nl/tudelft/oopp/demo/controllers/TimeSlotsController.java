package nl.tudelft.oopp.demo.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.models.ReservationRequestModel;
import nl.tudelft.oopp.demo.models.RoomReservation;
import nl.tudelft.oopp.demo.models.TimeSlots;

public class TimeSlotsController {


    /**
     * Gets all available timeSlots for a specific room in a specific date.
     * @param roomName the name of the room we want the available timeslots for
     * @param date the date of interest
     * @return A list of all available timeslots in that classroom on a specific date
     */
    public static List<TimeSlots> getAvailableTimeSlots(String roomName, String date) {
        String mapping = "AvailableTimeSlots/" + roomName + "/" + date;
        HttpResponse<String> response = ServerCommunication.getUtility(mapping);
        if (response == null) {
            return null;
        }

        Type listType = new TypeToken<ArrayList<TimeSlots>>(){}.getType();
        List<TimeSlots> timeSlots = new Gson().fromJson(response.body(), listType);
        return timeSlots;
    }


    /**
     * creates a reservation using the template of a reservation request model.
     * @param reservationRequestModel a room reservation request with all the required data
     *          to make a room reservation (room, timeslot, date, user making the request)
     * @return true if successful, false otherwise
     */
    public static boolean createReservation(ReservationRequestModel reservationRequestModel) {
        HttpResponse<String> response = ServerCommunication.postUtility(
                "createReservation/",reservationRequestModel);
        if (response == null || response.statusCode() != 200) {
            return false;
        }
        return true;
    }

    /**
     * Gets a list of holiday days to exclude from the agenda.
     * @return A list of dates which are holidays
     */
    public static List<LocalDate> getHolidayDays() {
        String mapping = "holidays/";
        HttpResponse<String> response = ServerCommunication.getUtility(mapping);
        if (response == null) {
            return null;
        }
        List<LocalDate> dates = new ArrayList<>();
        String[] dateComponents1 = response.body().substring(2,response.body().length() - 2)
                .split("\",\"");
        System.out.println(dateComponents1[0]);
        System.out.println(dateComponents1[1]);

        for (String x: dateComponents1) {
            String[] dateComponents = x.split("-");
            dates.add(LocalDate.of(Integer.parseInt(dateComponents[0]),
                    Integer.parseInt(dateComponents[1]),
                    Integer.parseInt(dateComponents[2])));
        }


        return dates;
    }

    /**
     * Gets a list of reservations from students only for a specific room and date.
     * @param roomName name of the desired room
     * @param date date of the reservations
     * @return a list with the student reservations for that room and date
     */
    public static List<TimeSlots> getStudentReservations(String roomName, String date) {
        HttpResponse<String> response = ServerCommunication.getUtility("studentReservations");
        if (response == null) {
            return null;
        }

        Type listType = new TypeToken<ArrayList<RoomReservation>>(){}.getType();
        List<RoomReservation> reservations = new Gson().fromJson(response.body(), listType);

        List<TimeSlots> slots = new ArrayList<>();
        ListIterator<RoomReservation> iter = reservations.listIterator();

        while (iter.hasNext()) {
            RoomReservation reservation = iter.next();
            if (reservation.getClassRoom().getRoomName().equals(roomName)
                    && reservation.getDate().equals(date)) {
                slots.add(reservation.getTimeSlots());
            }
        }

        return slots;
    }
}
