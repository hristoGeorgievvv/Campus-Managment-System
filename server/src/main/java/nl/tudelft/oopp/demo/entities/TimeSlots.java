package nl.tudelft.oopp.demo.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "timeSlots")
public class TimeSlots extends AbstractModel {
    public TimeSlots() {
    }

    private String timeDuration;

    @OneToMany(mappedBy = "timeSlots")
    private List<RoomReservation> roomReservationList = new ArrayList<>();


    public TimeSlots(String timeDuration) {
        this.timeDuration = timeDuration;
    }

    public String getTimeDuration() {
        return timeDuration;
    }

    /**.
     * EqualsMethod for TimeSlots
     * @param other Method to be equal
     * @return If its equal or not
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
            return this.timeDuration.equals(that.timeDuration);
        }
        return false;
    }
}

