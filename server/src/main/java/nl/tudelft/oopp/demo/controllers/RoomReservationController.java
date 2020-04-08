package nl.tudelft.oopp.demo.controllers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.validation.Valid;
import nl.tudelft.oopp.demo.entities.ClassRoom;
import nl.tudelft.oopp.demo.entities.RoomReservation;
import nl.tudelft.oopp.demo.entities.TimeSlots;
import nl.tudelft.oopp.demo.repositories.ClassRoomRepository;
import nl.tudelft.oopp.demo.repositories.RoomReservationRepository;
import nl.tudelft.oopp.demo.repositories.TimeSlotsRepository;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import nl.tudelft.oopp.demo.services.ReservationRequestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class RoomReservationController implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomReservationController.class);

    @Autowired
    RoomReservationRepository roomReservationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ClassRoomRepository classRoomRepository;
    @Autowired
    TimeSlotsRepository timeSlotsRepository;


    /**
     * Run method, contains the deleting method of expired reservations.
     * @param args does nothing
     * @throws Exception that isnt handled
     */
    @Override
    public void run(String... args) throws Exception {
        ScheduledExecutorService execService
                = Executors.newScheduledThreadPool(1);

        LocalDateTime start = LocalDateTime.now();
        // Hour + 1, set Minute and Second to 00
        LocalDateTime end = start.plusHours(1).truncatedTo(ChronoUnit.HOURS);

        // Get Duration
        Duration duration = Duration.between(start, end);
        long seconds = duration.toSeconds() + 5;

        execService.scheduleAtFixedRate(() -> {
            LocalDateTime currentTime = LocalDateTime.now();
            List<RoomReservation> reservations = roomReservationRepository.findAll();
            if (!reservations.isEmpty()) {
                for (RoomReservation reservation : reservations) {

                    LocalDateTime slottime = reservationTime(reservation);

                    if (currentTime.compareTo(slottime) > 0) {
                        LOGGER.info("--Reservation " + reservation.getId() + " at "
                                + slottime + " is expired...flushing--");
                        LOGGER.info("-----------------------------"
                                + "-------------------------------");
                        try {
                            roomReservationRepository.deleteById(reservation.getId());
                        } catch (Exception e) {
                            LOGGER.error("--Reservation Doesn't Exist--");
                        }
                    }
                }
            }
            LOGGER.info("--Expired Reservations Flushed, Current time: "
                    + new java.util.Date() + "--");

        }, seconds, 3600L, TimeUnit.SECONDS);
        LOGGER.info("--Server Started, reservation flush will occur in " + seconds + " seconds--");
    }

    /**
     * Creates LocalDateTime object from room reservation.
     * @param res Room reservation
     * @return LocalDateTime object
     */
    public LocalDateTime reservationTime(RoomReservation res) {

        int endtime = Integer.parseInt(
                res.getTimeSlots().getTimeDuration().substring(6, 8));

        int year = Integer.parseInt(
                res.getDateOfReservation().substring(6));

        int month = Integer.parseInt(
                res.getDateOfReservation().substring(3, 5));

        int day = Integer.parseInt(
                res.getDateOfReservation().substring(0, 2));

        return LocalDateTime.of(year, month, day, endtime, 0);
    }


    /**
     * Constructor used in testing.
     *
     * @param rrep room repository
     * @param urep user repository
     * @param crep classroomrepository
     * @param trep timeslotrepository
     */

    public RoomReservationController(
            RoomReservationRepository rrep, UserRepository urep,
            ClassRoomRepository crep, TimeSlotsRepository trep) {
        this.roomReservationRepository = rrep;
        this.userRepository = urep;
        this.classRoomRepository = crep;
        this.timeSlotsRepository = trep;
    }

    /**
     * GET Endpoint to retrieve all available TimeSlots.
     *
     * @return list of all available {@link TimeSlots} in the repository, or "No available".
     */
    @GetMapping("timeSlots")
    public List<TimeSlots> getAllAvailableTimeslots(
            @RequestBody ReservationRequestModel requestModel) {
        ClassRoom classRoom = classRoomRepository.getClassRoomById(requestModel.getRoomId());
        List<RoomReservation> roomReservations = classRoom.getRoomReservationList();
        List<TimeSlots> timeSlots = timeSlotsRepository.findAll();
        List<TimeSlots> busyTimeSlots = new ArrayList<>();
        for (RoomReservation rm : roomReservations) {
            busyTimeSlots.add(rm.getTimeSlots());
        }

        timeSlots.removeAll(busyTimeSlots);

        return timeSlots;
    }

    /**
     * Post Endpoint to make a reservation.
     *
     * @return the newly created reservation.
     */
    @GetMapping("reservations")
    public Object sendReservations() {
        List<RoomReservation> roomList = roomReservationRepository.findAll();
        if (roomList.isEmpty()) {
            return "No reservations Found!";
        }
        return roomList;
    }

    /**
     * Gets the current reservations given a certain users username.
     * @param userName username of user
     * @return list of reservations
     */
    @GetMapping("reservations/{userName}")
    public Object sendReservationsPerUser(@PathVariable String userName) {

        List<RoomReservation> roomList = roomReservationRepository.findByUserUserName(userName);

        /* This breaks other functions because it thinks it wants a json. This is commented in
        case something else depends on it being a string.

        if (roomList.isEmpty()) {  **
            return "No reservations Found!";
        }*/

        return roomList;
    }

    /**
     * Deletes a room reservation given the id of the reservation.
     * @param reservationId id of the reservation
     */
    @DeleteMapping(value = "/deleteReservation/{id}")
    public void deleteReservation(@PathVariable long reservationId) {
        if (roomReservationRepository.existsById(reservationId)) {
            roomReservationRepository.deleteById(reservationId);
        }
    }

    /**
     * Deletes a room reservation given the id of the reservation.
     */
    @PostMapping(value = "/deleteReservation", consumes = {"application/json"})
    public void deleteReservationBySlot(@Valid @RequestBody ReservationRequestModel rs) {

        String date = rs.getDate();
        String userName = rs.getUserName();
        String timeslot = rs.getTimeSlot();
        Long roomId = rs.getRoomId();

        Long timeSlotId = timeSlotsRepository.getByTimeDuration(timeslot).getId();

        RoomReservation deleteres = roomReservationRepository.findByAttributes(
                roomId, timeSlotId, date);
        if (roomReservationRepository.existsById(deleteres.getId())) {
            roomReservationRepository.deleteById(deleteres.getId());
        }
    }

    /**
     * Gets all reservations that are reserved by students.
     * @return reservations that were reserved by students
     */
    @GetMapping("studentReservations")
    public List<RoomReservation> studentTimeSlots() {
        List<Long> ids = roomReservationRepository.getStudentReservations();
        List<RoomReservation> slots = new ArrayList<>();

        ListIterator<Long> iter = ids.listIterator();

        while (iter.hasNext()) {
            slots.add(roomReservationRepository.getById(iter.next()));
        }
        return slots;
    }

}
