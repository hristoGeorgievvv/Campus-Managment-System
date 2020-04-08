package nl.tudelft.oopp.demo.repositories;

import java.util.List;
import nl.tudelft.oopp.demo.entities.FoodMenu;
import nl.tudelft.oopp.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface FoodMenuRepository extends JpaRepository<FoodMenu, Long> {
    List<FoodMenu> findFoodMenuByFoodVendorVendorName(String name);

    @Query(value = "SELECT * FROM foodMenu WHERE foodName = :name", nativeQuery = true)
    FoodMenu findByName(@Param("name") String name);

}
