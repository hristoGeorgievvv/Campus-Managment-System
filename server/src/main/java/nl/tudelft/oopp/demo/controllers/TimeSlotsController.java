package nl.tudelft.oopp.demo.controllers;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;

import nl.tudelft.oopp.demo.entities.ClassRoom;
import nl.tudelft.oopp.demo.entities.RoomReservation;
import nl.tudelft.oopp.demo.entities.TimeSlots;
import nl.tudelft.oopp.demo.entities.User;
import nl.tudelft.oopp.demo.repositories.ClassRoomRepository;
import nl.tudelft.oopp.demo.repositories.HolidaysRepository;
import nl.tudelft.oopp.demo.repositories.RoomReservationRepository;
import nl.tudelft.oopp.demo.repositories.TimeSlotsRepository;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import nl.tudelft.oopp.demo.services.ReservationRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TimeSlotsController {

    @Autowired
    ClassRoomRepository classRoomRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TimeSlotsRepository timeSlotsRepository;

    @Autowired
    RoomReservationRepository roomReservationRepository;

    @Autowired
    HolidaysRepository holidaysRepository;

    /**.
     * Constructor used in testing
     * @param classRoomRepository ClassroomRepository
     * @param userRepository UserRepository
     * @param timeSlotsRepository TimeSlotRepository
     * @param roomReservationRepository RoomRepository
     */
    public TimeSlotsController(ClassRoomRepository classRoomRepository,
                               UserRepository userRepository,
                               TimeSlotsRepository timeSlotsRepository,
                               RoomReservationRepository roomReservationRepository,
                               HolidaysRepository holidaysRepository) {
        this.classRoomRepository = classRoomRepository;
        this.roomReservationRepository = roomReservationRepository;
        this.timeSlotsRepository = timeSlotsRepository;
        this.userRepository = userRepository;
        this.holidaysRepository = holidaysRepository;
    }


    /**
     * Get endpoint to find all available TimeSlots in a specific room on a specific date.
     * @param roomName the name of the room the TimeSlots are for
     * @param date the date the available TimeSlots will be on
     * @return a list with all the available TimeSlots for that room on a specified date
     */
    @GetMapping(value = "/AvailableTimeSlots/{roomName}/{date}")
    public List<TimeSlots> sendAvailAbleTimeSlots(@PathVariable String roomName,
                                                  @PathVariable String date) {

        List<RoomReservation> roomReservations = roomReservationRepository
                .findByClassRoom_RoomNameAndDateOfReservation(roomName,date);
        List<TimeSlots> timeSlots = Arrays.asList(new TimeSlots[10]);

        timeSlots = timeSlotsRepository.findAll();

        for (RoomReservation roomReservation:roomReservations) {
            timeSlots.remove(roomReservation.getTimeSlots());
        }

        Collections.sort(timeSlots,(o1, o2) -> o1.getTimeDuration()
                .compareTo(o2.getTimeDuration()));

        return timeSlots;
    }

    /**
     * Post endpoint which takes in a json file and saves the incoming room into the database.
     * @param rs the information of the room which will be reserved, in the format of
     *           a room reservation request model
     * @return the room reservation that was just made
     */
    @PostMapping(value = "/createReservation", consumes = {"application/json"})
    public RoomReservation createNewReservation(@Valid @RequestBody ReservationRequestModel rs) {
        String date = rs.getDate();
        String userName = rs.getUserName();
        String timeslot = rs.getTimeSlot();
        Long roomId = rs.getRoomId();

        ClassRoom classRoom = classRoomRepository.getClassRoomById(roomId);
        User user = userRepository.getByUserName(userName);
        TimeSlots timeSlot = timeSlotsRepository.getByTimeDuration(timeslot);
        RoomReservation roomReservation = new RoomReservation(date,user,classRoom,timeSlot);
        return roomReservationRepository.save(roomReservation);
    }

    @GetMapping("holidays")
    public Object sendHolidays() {
        List<Date> holidays = holidaysRepository.getHolidayDates();
        return holidays;
    }
}
