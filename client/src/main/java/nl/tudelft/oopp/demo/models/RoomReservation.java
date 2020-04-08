package nl.tudelft.oopp.demo.models;

public class RoomReservation {

    private Long id;

    public void setDate(String date) {
        this.dateOfReservation = date;
    }

    /**
     * String for number of people.
     */
    private  String numberOfPeople;

    /**
     * User object.
     */
    private  User user;

    /**
     * Classroom object.
     */
    private ClassRoom classRoom;

    /**
     * Timeslot object.
     */
    private TimeSlots timeSlots;

    private String dateOfReservation;


    /**
     * No arg constructor.
     */
    public RoomReservation(){
    }

    public String getDate() {
        return dateOfReservation;
    }

    /**
     * Main constructor with id.
     * @param user Who made reservation.
     * @param classRoom Where is reservation.
     * @param timeSlots What timeslot is this reservation for.
     */
    public RoomReservation(long id, String date, User user,
                           ClassRoom classRoom, TimeSlots timeSlots) {
        this.id = id;
        this.dateOfReservation = date;
        this.user = user;
        this.classRoom = classRoom;
        this.timeSlots = timeSlots;
    }

    /**
     * Main constructor.
     * @param user Who made reservation.
     * @param classRoom Where is reservation.
     * @param timeSlots What timeslot is this reservation for.
     */
    public RoomReservation(String date, User user,
                           ClassRoom classRoom, TimeSlots timeSlots) {
        this.dateOfReservation = date;
        this.user = user;
        this.classRoom = classRoom;
        this.timeSlots = timeSlots;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public TimeSlots getTimeSlots() {
        return timeSlots;
    }

    /**.
     * Equals method for RoomReservation
     * @param other Object to compare to
     * @return Whether or not they are equal
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (other instanceof RoomReservation) {
            RoomReservation that = (RoomReservation) other;
            return this.dateOfReservation.equals(that.dateOfReservation)
                    && this.user.equals(that.user)
                    && this.classRoom.equals(that.classRoom)
                    && this.timeSlots.equals(that.timeSlots);
        }
        return false;
    }
}
