package nl.tudelft.oopp.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;





@Entity
@Table(name = "ClassRoom")
public class ClassRoom extends AbstractModel {
    public ClassRoom() {
    }

    /**
     * Name of the Room.
     */
    @NotBlank
    @Size(min = 3, max = 100)
    private String roomName;

    /**
     * Number of people the room can fit.
     */
    @NotNull
    @Min(3)
    @Max(90)
    private int numberOfPeople;

    /**
     * Building the room is in.
     */
    @ManyToOne
    @JoinColumn()
    @JsonIgnore
    private Building building;

    public boolean isBlackBoard() {
        return blackBoard;
    }

    public boolean isWhiteBoard() {
        return whiteBoard;
    }

    public boolean isProjectorBoard() {
        return projectorBoard;
    }

    @Column(columnDefinition = "tinyint(1) default 1")
    private boolean blackBoard;

    @Column(columnDefinition = "tinyint(1) default 1")
    private boolean whiteBoard;

    @Column(columnDefinition = "tinyint(1) default 1")
    private boolean projectorBoard;


    /**
     * Reservations associated with this classroom.
     */
    @OneToMany(mappedBy = "classRoom",cascade = CascadeType.REMOVE)
    @JsonManagedReference
    @JsonIgnore
    private List<RoomReservation> roomReservationList = new ArrayList<>();

    public String getRoomName() {
        return roomName;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    @JsonIgnore
    public Building getBuilding() {
        return building;
    }

    @JsonIgnore
    public List<RoomReservation> getRoomReservationList() {
        return roomReservationList;
    }

    /**.
     * Used in testing
     * @param reservation RoomReservation
     */
    @JsonIgnore
    public void addReservationToList(RoomReservation reservation) {
        this.roomReservationList.add(reservation);
    }

    /**
     * constructor for the class Classroom.
     * @param roomName name of the room
     * @param building building the room is in
     * @param numberOfPeople the capacity of the room
     */
    public ClassRoom(String roomName, Building building, int numberOfPeople) {
        this.roomName = roomName;
        this.building = building;
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClassRoom classRoom = (ClassRoom) o;
        return Objects.equals(roomName, classRoom.roomName)
                && building.equals(classRoom.building);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomName);
    }

}
