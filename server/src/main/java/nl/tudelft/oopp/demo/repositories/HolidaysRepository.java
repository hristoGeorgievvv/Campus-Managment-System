package nl.tudelft.oopp.demo.repositories;

import java.util.Date;
import java.util.List;
import nl.tudelft.oopp.demo.entities.TimeSlots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HolidaysRepository extends JpaRepository<TimeSlots,Long> {
    @Query(value = "SELECT * FROM holidays", nativeQuery = true)
    List<Date> getHolidayDates();
}
