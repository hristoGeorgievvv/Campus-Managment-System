package nl.tudelft.oopp.demo.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.models.Building;
import nl.tudelft.oopp.demo.models.ClassRoom;

public class ClassroomController {

    /**
     * Generates data for all classrooms.
     * @return a hashmap with the buildings and a list of classrooms in each building
     */
    public static HashMap<Building, List<ClassRoom>> generateData() {
        List<Building> buildings = BuildingController.getBuildings();

        if (buildings == null) {
            return null;
        }

        HashMap<Building, List<ClassRoom>> data = new HashMap<>();

        for (Building building: buildings) {
            List<ClassRoom> classRooms = building.getClassrooms();
            if (classRooms == null) {
                continue;
            }
            for (ClassRoom classRoom:classRooms) {
                classRoom.setBuilding(building);
            }
            data.put(building,classRooms);
        }

        return data;
    }


    /**
     * Gets all classrooms in a specific building.
     * @param building the building we want to get the rooms for
     * @return a list with all the classrooms in the building
     */
    public static List<ClassRoom> getClassRooms(Building building) {
        HttpResponse<String> response = ServerCommunication.getUtility(
                "classrooms/byBuilding/" + building.getBuildingName());
        if (response == null || response.body().equals("No Classrooms Found!")) {
            return null;
        }

        Type listType = new TypeToken<ArrayList<ClassRoom>>(){}.getType();

        List<ClassRoom> classRooms = new Gson().fromJson(response.body(), listType);

        return classRooms;
    }
}
