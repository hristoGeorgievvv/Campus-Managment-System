package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.TimeSlots;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotsRepository extends JpaRepository<TimeSlots,Long> {
    TimeSlots getById(Long id);

    TimeSlots getByTimeDuration(String timeDuration);
}
