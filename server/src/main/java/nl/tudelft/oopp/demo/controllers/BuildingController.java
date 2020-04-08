package nl.tudelft.oopp.demo.controllers;

import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;

import nl.tudelft.oopp.demo.entities.Building;
import nl.tudelft.oopp.demo.repositories.BuildingReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Transactional
@RestController
public class BuildingController {

    @Autowired
    BuildingReposity buildingReposity;


    public BuildingController(BuildingReposity rep) {
        buildingReposity = rep;
    }

    /**
     * Returns a list of all buildings in the database.
     * @return A list of buildings that are in the database, or a string otherwise
     */
    @GetMapping("All_Buildings")
    public Object sendBuildings() {
        List<Building> buList = buildingReposity.findAll();
        if (buList.isEmpty()) {
            return "No Buildings Found!";
        }
        return buList;
    }

    /**
     * POST Endpoint to create a new building.
     *
     * @return the newly created  {@link Building} in the repository.
     */

    @PostMapping(value = "/createBuilding", consumes = {"application/json"})
    public Building createNewBuilding(@Valid @RequestBody Building building) {
        return buildingReposity.save(building);
    }


    @DeleteMapping(value = "/deleteBuilding/{buildingName}")
    public void deleteBuilding(@PathVariable String buildingName) {
        System.out.println(buildingName);
        buildingReposity.deleteByBuildingName(buildingName);
    }

    /**
     * updates the information in a building.
     * @param building The name of the building we want to update
     */
    @PutMapping(value = "/updateBuilding", consumes = {"application/json"})
    public void updateBuilding(@Valid @RequestBody Building building) {
        buildingReposity.setUBuilding(building.getOpeningHours(),
                building.getDepartment(),
                building.getLocation(),
                building.getNumberOfAvailablebikes(),
                building.getBuildingName());
        System.out.println(building.getOpeningHours());
    }
}
