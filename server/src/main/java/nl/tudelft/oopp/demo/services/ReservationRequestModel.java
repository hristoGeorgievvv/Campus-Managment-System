package nl.tudelft.oopp.demo.services;

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
     * Constructor for the reservation request model.
     * @param roomId id of the room we wish to reserve
     * @param date date of the reservation
     * @param timeSlot timeslot we want to reserve in
     * @param userName name of the user who is reserving the room
     */
    public ReservationRequestModel(Long roomId, String date, String timeSlot, String userName) {
        this.roomId = roomId;
        this.date = date;
        this.timeSlot = timeSlot;
        this.userName = userName;
    }

    /** If other is equal will return true if not false.
     * @param other object to compare
     * @return whether or not object is equal to this
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (other instanceof ReservationRequestModel) {
            ReservationRequestModel that = (ReservationRequestModel) other;
            return this.roomId == that.roomId
                    && this.date.equals(that.date)
                    && this.timeSlot.equals(that.timeSlot)
                    && this.userName.equals(that.userName);
        }
        return false;
    }
}
