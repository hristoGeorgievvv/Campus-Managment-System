package nl.tudelft.oopp.demo.repositories;

import java.util.List;
import nl.tudelft.oopp.demo.entities.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BuildingReposity extends JpaRepository<Building, Long> {
    Building findAllByBuildingName(String name);

    @Query("SELECT buildingName FROM Building")
    List<String> getBuildingNames();


    void deleteByBuildingName(String name);

    @Modifying
    @Query("update Building u set u.openingHours = ?1,"
            + " u.department= ?2, u.location =?3,"
            + "u.numberOfAvailablebikes = ?4"
            + " where u.buildingName = ?5")
    void setUBuilding(String openingHours,
                      String department,
                      String location,
                      int numberOfAvailablebikes,
                      String buildingName
                      );

    @Modifying
    @Query("UPDATE Building b SET b.numberOfAvailablebikes = :num "
            + "where b.buildingName = :buildingName")
    void setBuildingBikes(@Param("num") int num, @Param("buildingName") String buildingName);

}
