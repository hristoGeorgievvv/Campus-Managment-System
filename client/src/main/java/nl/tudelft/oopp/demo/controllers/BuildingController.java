package nl.tudelft.oopp.demo.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.models.Building;

public class BuildingController {


    /**
     * Returns a list of all buildings in the database.
     * @return A list of all buildings in the database
     */
    public static List<Building> getBuildings() {
        HttpResponse<String> response = ServerCommunication.getUtility("All_Buildings");
        if (response == null) {
            return null;
        }

        Type listType = new TypeToken<ArrayList<Building>>(){}.getType();
        List<Building> buildings = new Gson().fromJson(response.body(), listType);

        return buildings;
    }

    /**
     * Gets the names of the buildings from the database.
     * @return returns the list of building names from the server
     */

    public static List<String> getBuildingNames() {
        List<String> buildingNames = new ArrayList<String>();
        List<Building> buildings = BuildingController.getBuildings();
        if (buildings == null) {
            return null;
        }
        for (Building building: buildings) {
            buildingNames.add(building.getBuildingName());
        }
        return buildingNames;
    }

    /**
     * Deletes building from database based off of the building name.
     * @param buildingName String with the name of the building we want to remove
     * @return true if the building was removed, false otherwise
     */
    public static boolean deleteBuilding(String buildingName) {
        HttpResponse<String> status = ServerCommunication.deleteUtility(
                "deleteBuilding/" + buildingName);
        if (status == null || status.statusCode() != 200) {
            return false;
        }
        return true;
    }

    /**
     * Creates a building.
     * @param building The name of the building we want to create/check
     * @return true if there is a response
     */
    public static boolean createBuilding(Building building) {
        HttpResponse<String> status = ServerCommunication.postUtility("createBuilding/",building);
        if (status == null || status.statusCode() != 200) {
            return false;
        }
        return true;
    }

    /**
     * Updates a building.
     * @param building Name of the building we want to update
     * @return true if we get a response from the server, false otherwise
     */
    public static boolean updateBuilding(Building building) {
        HttpResponse<String> status = ServerCommunication.updateUtility("updateBuilding/",building);
        if (status == null || status.statusCode() != 200) {
            return false;
        }
        return true;
    }
}
