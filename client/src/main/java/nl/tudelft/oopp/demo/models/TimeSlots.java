package nl.tudelft.oopp.demo.models;

public class TimeSlots {

    private long id;

    private String timeDuration;

    public String getTimeDuration() {
        return timeDuration;
    }

    private boolean studentReservation;

    public boolean isStudentReservation() {
        return studentReservation;
    }

    public void setStudentReservation(boolean bool) {
        studentReservation = bool;
    }

    /**
     * no arg constructor.
     */
    public TimeSlots() {
        studentReservation = false;
    }

    /**
     * Constructor for timeslots, using id and time duration.
     * @param id timeslot id
     * @param timeDuration time duration
     */
    public TimeSlots(long id, String timeDuration) {
        studentReservation = false;
        this.id = id;
        this.timeDuration = timeDuration;
    }

    public long getId() {
        return id;
    }

    /**.
     * Equals method for TimeSlots.
     * @param other object to compare to
     * @return whether or not they are equal
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (other instanceof TimeSlots) {
            TimeSlots that = (TimeSlots) other;
            return this.id == that.id;
        }
        return false;
    }
}
