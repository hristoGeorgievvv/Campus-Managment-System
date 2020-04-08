package nl.tudelft.oopp.demo.repositories;

import java.util.List;
import nl.tudelft.oopp.demo.entities.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
    ClassRoom getClassRoomById(Long id);

    List<ClassRoom> findClassRoomByBuildingBuildingName(String name);

}
