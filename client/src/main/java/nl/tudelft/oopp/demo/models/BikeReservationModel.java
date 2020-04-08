package nl.tudelft.oopp.demo.models;

public class BikeReservationModel {
    private String userName;
    private String buildingName;

    /**
     * Model containing info about bike reservation.
     * @param userName name of requesting user
     * @param buildingName name of building to/from
     */
    public BikeReservationModel(String userName, String buildingName) {
        this.userName = userName;
        this.buildingName = buildingName;
    }

    public String getUserName() {
        return userName;
    }

    public String getBuildingName() {
        return buildingName;
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
        if (other instanceof BikeReservationModel) {
            BikeReservationModel that = (BikeReservationModel) other;
            return that.buildingName.equals(this.buildingName)
                    && that.userName.equals(this.userName);
        }
        return false;
    }
}
