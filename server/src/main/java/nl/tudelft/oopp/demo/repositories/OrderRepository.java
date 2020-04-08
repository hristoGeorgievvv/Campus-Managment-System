package nl.tudelft.oopp.demo.repositories;

import java.util.List;
import nl.tudelft.oopp.demo.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface OrderRepository extends JpaRepository<Order,Long> {

    Order getById(Long id);

    @Query(value = "SELECT * FROM orders WHERE user_id = :id", nativeQuery = true)
    List<Order> findByUserId(@Param("id") Long id);

}
