package nl.tudelft.oopp.demo.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import nl.tudelft.oopp.demo.entities.Building;
import nl.tudelft.oopp.demo.entities.User;
import nl.tudelft.oopp.demo.repositories.BuildingReposity;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import nl.tudelft.oopp.demo.services.BikeReservationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BikeReservationController {

    @Autowired
    BuildingReposity buildingReposity;

    @Autowired
    UserRepository userRepository;

    public BikeReservationController(BuildingReposity buildingReposity,
                                     UserRepository userRepository) {
        this.buildingReposity = buildingReposity;
        this.userRepository = userRepository;
    }

    /**
     * Checks if the specified user has a bicycle rented.
     * @return true if the user has a bicycle rented, false otherwise
     */

    @GetMapping(value = "bikeReservation/{username}")
    public boolean userHasBike(@PathVariable String username) {
        User user = userRepository.getByUserName(username);
        return user.getBike();
    }

    /**
     * this method is used to record that a bike reservation has been made.
     * @return  boolean of what new value is .
     */
    @Transactional
    @PostMapping(value = "createBikeReservation", consumes = {"application/json"})
    public boolean changeOrDeleteReservation(
            @Valid @RequestBody BikeReservationModel reservationModel) {
        String username = reservationModel.getUserName();
        User user = userRepository.getByUserName(username);
        Boolean bool = user.getBike();
        userRepository.setUserBike(!bool, username);

        String buildingName = reservationModel.getBuildingName();
        Building building = buildingReposity.findAllByBuildingName(buildingName);

        int currentBikes = building.getNumberOfAvailablebikes();

        if (!bool && currentBikes > 0) {
            buildingReposity.setBuildingBikes(currentBikes - 1,buildingName);
        } else {
            buildingReposity.setBuildingBikes(currentBikes + 1,buildingName);
        }

        return !bool;

    }
}