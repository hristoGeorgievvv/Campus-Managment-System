package nl.tudelft.oopp.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "room_reservations")
public class RoomReservation extends AbstractModel {
    public RoomReservation() {
    }

    /**
     * Date of reservation.
     */

    private String dateOfReservation;

    /**
     * The user associated with the current reservation.
     */
    @ManyToOne
    @JsonIgnore
    @JoinColumn()
    private User user;

    /**
     * The classRoom of the reservation.
     */
    @ManyToOne
    @JoinColumn(name = "classroom_id", referencedColumnName = "id")
    private ClassRoom classRoom;

    @ManyToOne
    @JoinColumn(name = "timeSlots_id", referencedColumnName = "id")
    private TimeSlots timeSlots;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.REMOVE)
    private List<Order> orderSet = new ArrayList<>();

    public String getDateOfReservation() {
        return dateOfReservation;
    }

    public User getUser() {
        return user;
    }

    public TimeSlots getTimeSlots() {
        return timeSlots;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    /**.
     * Equals method for RoomReservation
     * @param other Other Object to be compored
     * @return Whether or not the objects are equal
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

    /**
     * Constructor for the room reservation class.
     * @param dateOfReservation date of the reservation
     * @param user user who has reserved the room
     * @param classRoom classroom which has been reserved
     * @param timeSlots timeslot of the reservation
     */
    public RoomReservation(String dateOfReservation, User user,
                           ClassRoom classRoom, TimeSlots timeSlots) {
        this.dateOfReservation = dateOfReservation;
        this.user = user;
        this.classRoom = classRoom;
        this.timeSlots = timeSlots;
    }

}
