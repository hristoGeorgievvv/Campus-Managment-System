package nl.tudelft.oopp.demo.models;

import java.util.List;
import java.util.Objects;

public class Building  {
    public Building() {

    }

    /**
     * Name of the building.
     */
    private String buildingName;

    /**
     * Department of the Building.
     */
    private String department;

    /**
     * Location of the building.
     */
    private String location;

    /**
     * Opening Hours of the bulilding.
     */
    private String openingHours;

    private int numberOfAvailablebikes;

    private List<ClassRoom> classRoomSet;

    public int getNumberOfAvailablebikes() {
        return numberOfAvailablebikes;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public String getDepartment() {
        return department;
    }

    public String getLocation() {
        return location;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public List<ClassRoom> getClassrooms() {
        return classRoomSet;
    }

    /**
     * The constructor of the Building.
     * @param buildingName the name of the Building
     * @param department the department of the building
     * @param location the location of the building
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

    /**
     * Alternate constructor for Building, includes the classrooms in the building.
     * @param openingHours opening hours of the building
     * @param buildingName name of the building
     * @param department department the building is a part of
     * @param location location of the building
     * @param numberOfAvailablebikes number of available bikes in the building
     * @param classRooms classrooms the building has
     */
    public Building(String openingHours,
                    String buildingName,
                    String department,
                    String location,
                    int numberOfAvailablebikes,
                    List<ClassRoom> classRooms) {
        this.openingHours = openingHours;
        this.buildingName = buildingName;
        this.department = department;
        this.location = location;
        this.numberOfAvailablebikes = numberOfAvailablebikes;
        this.classRoomSet = classRooms;
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
                &&
                Objects.equals(location, building.location);
    }
}
