package nl.tudelft.oopp.demo.models;

import java.util.Objects;

public class ReservationRequestModel {

    private Long roomId;
    private String date;
    private String timeSlot;
    private String userName;


    public String getDate() {
        return date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public String getUserName() {
        return userName;
    }

    public Long getRoomId() {
        return roomId;
    }

    /**
     * Constructor for the Reservation request model.
     * @param roomId id of the room
     * @param date date of the reservation request
     * @param timeSlot timeslot
     * @param userName name of the user who wants to make the reservation
     */
    public ReservationRequestModel(Long roomId, String date, String timeSlot, String userName) {
        this.roomId = roomId;
        this.date = date;
        this.timeSlot = timeSlot;
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReservationRequestModel that = (ReservationRequestModel) o;
        return Objects.equals(roomId, that.roomId)
                &&
                Objects.equals(date, that.date)
                &&
                Objects.equals(timeSlot, that.timeSlot)
                &&
                Objects.equals(userName, that.userName);
    }
}
