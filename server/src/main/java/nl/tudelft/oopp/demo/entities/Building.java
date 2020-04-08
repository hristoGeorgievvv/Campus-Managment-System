package nl.tudelft.oopp.demo.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "buildings")
public class Building extends AbstractModel {
    public Building() {
    }

    /**
     * Name of the building.
     */
    @Size(min = 3, max = 100)
    private String buildingName;

    /**
     * Department of the Building.
     */

    @Size(min = 3, max = 100)
    private String department;

    /**
     * Location of the building.
     */
    @Size(min = 3, max = 100)
    private String location;

    /**
     * Number of available bikes.
     */
    private int numberOfAvailablebikes;

    private String openingHours;

    /**
     * ClassRooms of the bulding.
     */
    @OneToMany(mappedBy = "building", cascade = CascadeType.REMOVE)
    private List<ClassRoom> classRoomSet = new ArrayList<>();


    public String getBuildingName() {
        return buildingName;
    }

    public String getDepartment() {
        return department;
    }

    public String getLocation() {
        return location;
    }

    public int getNumberOfAvailablebikes() {
        return numberOfAvailablebikes;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public List<ClassRoom> getClassRoomSet() {
        return this.classRoomSet;
    }

    //Used in testing
    public void addClassroomToSet(ClassRoom classroom) {
        this.classRoomSet.add(classroom);
    }

    public void setNumberOfAvailablebikes(int numberOfAvailablebikes) {
        this.numberOfAvailablebikes = numberOfAvailablebikes;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    /**
     * Constructor for class Building.
     * @param openingHours .
     * @param buildingName .
     * @param department .
     * @param location .
     * @param numberOfAvailablebikes .
     */
    public Building(String openingHours,
                    String buildingName,
                    String department,
                    String location,
                    int numberOfAvailablebikes) {
        this.openingHours = openingHours;
        this.buildingName = buildingName;
        this.department = department;
        this.location = location;
        this.numberOfAvailablebikes = numberOfAvailablebikes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Building building = (Building) o;
        return Objects.equals(buildingName, building.buildingName)
                && Objects.equals(department, building.department)
                && Objects.equals(location, building.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildingName, department, location);
    }
}
