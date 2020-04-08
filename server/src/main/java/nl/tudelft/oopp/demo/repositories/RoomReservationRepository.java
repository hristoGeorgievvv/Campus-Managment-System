package nl.tudelft.oopp.demo.repositories;

import java.util.List;
import nl.tudelft.oopp.demo.entities.RoomReservation;
import nl.tudelft.oopp.demo.entities.TimeSlots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomReservationRepository extends JpaRepository<RoomReservation,Long> {
    List<RoomReservation> findByClassRoom_RoomNameAndDateOfReservation(
            String classRoomName,String dateOfReservation);

    List<RoomReservation> findByUserUserName(String name);

    RoomReservation getById(Long id);

    @Query(value = "SELECT * FROM room_reservations WHERE classroom_id = :classID AND"
            + " timeSlots_id = :slotID AND dateOfReservation = :date", nativeQuery = true)
    RoomReservation findByAttributes(@Param("classID") Long classID,
                                     @Param("slotID") Long slotID, @Param("date") String date);

    @Query(value = "SELECT room_reservations.id FROM room_reservations JOIN users ON"
            + " user_id = users.id WHERE type = 'Student'", nativeQuery = true)
    List<Long> getStudentReservations();
}
