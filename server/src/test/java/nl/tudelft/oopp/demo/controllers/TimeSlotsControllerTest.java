package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.tudelft.oopp.demo.entities.Building;
import nl.tudelft.oopp.demo.entities.ClassRoom;
import nl.tudelft.oopp.demo.entities.RoomReservation;
import nl.tudelft.oopp.demo.entities.TimeSlots;
import nl.tudelft.oopp.demo.entities.User;
import nl.tudelft.oopp.demo.repositories.BuildingReposity;
import nl.tudelft.oopp.demo.repositories.ClassRoomRepository;
import nl.tudelft.oopp.demo.repositories.HolidaysRepository;
import nl.tudelft.oopp.demo.repositories.RoomReservationRepository;
import nl.tudelft.oopp.demo.repositories.TimeSlotsRepository;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import nl.tudelft.oopp.demo.services.ReservationRequestModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;



@DataJpaTest
public class TimeSlotsControllerTest {

    @Autowired
    private RoomReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRep;

    @Autowired
    private ClassRoomRepository classRep;

    @Autowired
    private TimeSlotsRepository timeRep;

    @Autowired
    private BuildingReposity buildingRep;

    @Autowired
    private HolidaysRepository holidaysRepository;

    private Building testbuilding = new Building(
            "Test_Name",
            "Test_Department",
            "Test_Location",
            "TestingHours",
            5
    );
    private ClassRoom classroom = new ClassRoom(
            "test",
            testbuilding,
            4
    );
    private User user = new User(
            "testName",
            "testType",
            "testEmail",
            "testPword",
            true
    );
    private TimeSlots timeslot = new TimeSlots("testDuration");
    private TimeSlots timeslot2 = new TimeSlots("testDuration2");
    private RoomReservation roomReservation = new RoomReservation(
            "testDate",
            user,
            classroom,
            timeslot
    );

    /**
     * sendAvailableTimeSlots testing.
     */

    @Test
    public void sendAvailableTimeSlotsTest() {
        buildingRep.save(testbuilding);
        classRep.save(classroom);
        userRep.save(user);
        timeRep.save(timeslot);
        timeRep.save(timeslot2);
        reservationRepository.save(roomReservation);

        TimeSlotsController myTsController = new TimeSlotsController(
                classRep, userRep, timeRep, reservationRepository, holidaysRepository);
        assertEquals(1, myTsController.sendAvailAbleTimeSlots("test", "testDate").size(),
                "Should be 1 available timeslot");
        assertEquals(timeslot2, myTsController.sendAvailAbleTimeSlots("test", "testDate").get(0),
                "Timeslot given is incorrect");
    }

    @Test
    public void sendAvailableTimeSlotsTestAllBusy() {
        buildingRep.save(testbuilding);
        classRep.save(classroom);
        userRep.save(user);
        timeRep.save(timeslot);
        reservationRepository.save(roomReservation);

        TimeSlotsController myTimeSlotController = new TimeSlotsController(classRep,
                userRep, timeRep, reservationRepository, holidaysRepository);
        assertEquals(0, myTimeSlotController.sendAvailAbleTimeSlots("test",
                "testDate").size(), "Should be no available timeslots");
    }

    @Test
    public void sendAvailableTimeSlotsTestAllFree() {
        buildingRep.save(testbuilding);
        classRep.save(classroom);
        userRep.save(user);
        timeRep.save(timeslot);

        TimeSlotsController myTimeSlotController = new TimeSlotsController(
                classRep, userRep, timeRep, reservationRepository, holidaysRepository);
        assertEquals(1, myTimeSlotController.sendAvailAbleTimeSlots(
                "test", "testDate").size(), "Should be 1 available timeslots");
        assertEquals(timeslot, myTimeSlotController.sendAvailAbleTimeSlots(
                "test", "testDate").get(0), "Incorrect timeslot given");
    }

    /**
     * CreateNewReservation testing.
     */
    @Test
    public void createNewReservationTest() {
        buildingRep.save(testbuilding);
        classRep.save(classroom);
        userRep.save(user);
        timeRep.save(timeslot);

        ReservationRequestModel reservationRequestModel = new ReservationRequestModel(
                classroom.getId(),
                "testDate",
                "testDuration",
                "testName"
        );

        TimeSlotsController myTimeSlotController = new TimeSlotsController(
                classRep, userRep, timeRep, reservationRepository, holidaysRepository);
        myTimeSlotController.createNewReservation(reservationRequestModel);
        assertEquals(1, reservationRepository.findAll().size(),
                "Expected new Reservation");

        RoomReservation expectedReservation = new RoomReservation(
                "testDate",
                user,
                classroom,
                timeslot
        );

        assertEquals(expectedReservation, reservationRepository.findAll().get(0),
                "Created reservation incorrectly");
    }

}