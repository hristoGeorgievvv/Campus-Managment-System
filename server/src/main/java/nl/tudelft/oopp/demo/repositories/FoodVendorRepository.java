package nl.tudelft.oopp.demo.repositories;

import java.util.List;
import nl.tudelft.oopp.demo.entities.FoodVendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FoodVendorRepository extends JpaRepository<FoodVendor, Long> {

    @Query("SELECT vendorName FROM FoodVendor ")
    List<String> getVendorNames();

}
