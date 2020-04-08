package nl.tudelft.oopp.demo.models;

import java.util.Objects;

public class ClassRoom {

    private long id;

    /**
     * Building where classroom came from.
     */
    private Building building;

    /**
     * Name of the room.
     */
    private String roomName;



    /**
     *  Number of people the room can fit.
     */
    private int numberOfPeople;

    /**
     * No arg constructor.
     */
    public ClassRoom() {

    }

    public long getid() {
        return id;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public void setBlackBoard(boolean blackBoard) {
        this.blackBoard = blackBoard;
    }

    public void setWhiteBoard(boolean whiteBoard) {
        this.whiteBoard = whiteBoard;
    }

    public void setProjectorBoard(boolean projectorBoard) {
        this.projectorBoard = projectorBoard;
    }

    /**
     * Extended constructor for classroom.
     * @param building building that the classroom is in
     * @param roomName the name of the classroom itself
     * @param numberOfPeople the capacity of the room (max number of people)
     * @param blackBoard if there is a blackboard in the room
     * @param whiteBoard if there is a whiteboard in the room
     * @param projectorBoard if there is a projector in the room
     */
    public ClassRoom(Building building, String roomName, int numberOfPeople,
                     boolean blackBoard, boolean whiteBoard, boolean projectorBoard) {
        this.building = building;
        this.roomName = roomName;
        this.numberOfPeople = numberOfPeople;
        this.blackBoard = blackBoard;
        this.whiteBoard = whiteBoard;
        this.projectorBoard = projectorBoard;
    }

    public boolean isBlackBoard() {
        return blackBoard;
    }

    public boolean isWhiteBoard() {
        return whiteBoard;
    }

    public boolean isProjectorBoard() {
        return projectorBoard;
    }

    /**
     * Main constructor.
     * @param roomName name of room.
     * @param numberOfPeople room can fit
     */
    public ClassRoom(long id,String roomName, int numberOfPeople) {
        this.id = id;
        this.roomName = roomName;
        this.numberOfPeople = numberOfPeople;
    }

    private boolean blackBoard;

    private boolean whiteBoard;

    private boolean projectorBoard;

    /**
     * Room name getter.
     * @return string containing room name.
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Building getter.
     * @return building object.
     */
    public Building getBuilding() {
        return building;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setBuilding(Building building) {
        this.building = building;
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
        return id == classRoom.id
                &&
                Objects.equals(building, classRoom.building)
                &&
                Objects.equals(roomName, classRoom.roomName);
    }

}
