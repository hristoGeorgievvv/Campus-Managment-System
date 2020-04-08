package nl.tudelft.oopp.demo.views;

import java.time.LocalDate;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import jfxtras.scene.control.agenda.Agenda;
import nl.tudelft.oopp.demo.controllers.RoomReservationController;
import nl.tudelft.oopp.demo.models.RoomReservation;

public class CurrentReservationsScreen {

    /**
     * Creates a pop up window that contains an agenda with the user's reservations for the week.
     */
    public static void getCurrentReservations() {
        Agenda currentReservations = new Agenda();
        currentReservations.setAllowDragging(false);
        List<RoomReservation> reservations = RoomReservationController.getReservationsPerUser();

        if (reservations.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No registrations");
            alert.setHeaderText("You have no reservations");
            alert.showAndWait();
            return;
        }

        prepareReservations(currentReservations, reservations);

        Stage stage = new Stage();
        stage.getIcons().add(new Image("Images/TU-Delft-Logo.jpg"));
        stage.setTitle("My Reservations");
        Scene scene = new Scene(currentReservations);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Gets current reservations for the agendas in the student and staff info screens.
     * @param currentReservations the agenda we are giving information to
     */
    public static void getCurrentReservations(Agenda currentReservations) {
        List<RoomReservation> reservations = RoomReservationController.getReservationsPerUser();
        prepareReservations(currentReservations, reservations);
    }

    /**
     * Sets the agenda to have all its reservations.
     * @param currentReservations the agenda that is being edited
     * @param reservations the reservations to add to the agenda
     */
    private static void prepareReservations(Agenda currentReservations,
                                            List<RoomReservation> reservations) {
        for (RoomReservation rr : reservations) {

            String[] dateComponents = rr.getDate().split("-");
            int day = Integer.parseInt(dateComponents[0]);
            int month = Integer.parseInt(dateComponents[1]);
            int year = Integer.parseInt(dateComponents[2]);

            String[] hours = rr.getTimeSlots().getTimeDuration().split("-");
            int startHour = Integer.parseInt(hours[0].substring(0, 2));
            int endHour = Integer.parseInt(hours[1].substring(0, 2));

            currentReservations.appointments().addAll(
                    new Agenda.AppointmentImplLocal()
                            .withStartLocalDateTime(LocalDate.of(year, month, day)
                                    .atTime(startHour, 0))
                            .withEndLocalDateTime(LocalDate.of(year, month, day).atTime(endHour, 0))
                            .withLocation(rr.getClassRoom().getRoomName())
                            .withAppointmentGroup(new Agenda.AppointmentGroupImpl()
                                    .withStyleClass("group1"))
                            .withSummary(rr.getClassRoom().getRoomName())
            );

        }
    }
}
