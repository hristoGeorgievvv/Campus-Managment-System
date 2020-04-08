package nl.tudelft.oopp.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import nl.tudelft.oopp.demo.entities.Building;
import nl.tudelft.oopp.demo.entities.ClassRoom;
import nl.tudelft.oopp.demo.entities.RoomReservation;
import nl.tudelft.oopp.demo.entities.TimeSlots;
import nl.tudelft.oopp.demo.entities.User;
import nl.tudelft.oopp.demo.repositories.BuildingReposity;
import nl.tudelft.oopp.demo.repositories.ClassRoomRepository;
import nl.tudelft.oopp.demo.repositories.RoomReservationRepository;
import nl.tudelft.oopp.demo.repositories.TimeSlotsRepository;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import nl.tudelft.oopp.demo.services.ReservationRequestModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;



@DataJpaTest
public class RoomReservationControllerTest {

    @Autowired
    private RoomReservationRepository roomRep;

    @Autowired
    private UserRepository userRep;

    @Autowired
    private ClassRoomRepository classRep;

    @Autowired
    private TimeSlotsRepository timeRep;

    @Autowired
    private BuildingReposity buildingRep;

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
            false
    );
    private TimeSlots timeslot = new TimeSlots("testDuration");
    private TimeSlots timeslot2 = new TimeSlots("testFreeTimeslot");
    private RoomReservation roomReservation = new RoomReservation(
            "testDate",
            user,
            classroom,
            timeslot
    );
    private RoomReservation roomReservation2 = new RoomReservation(
            "testDate",
            user,
            classroom,
            timeslot2
    );

    /**
     * getAllAvailableTimeSlots testing.
     */
    @Test
    public void getAllAvailableTimeSlotTest() {
        buildingRep.save(testbuilding);
        classRep.save(classroom);
        userRep.save(user);
        timeRep.save(timeslot);
        timeRep.save(timeslot2);
        roomRep.save(roomReservation);

        //This needs to be here because classroomID is generated after its saved to a repository
        ReservationRequestModel reservationRequestModel = new ReservationRequestModel(
                classroom.getId(),
                "testDate",
                "testTimeslot",
                "testName"
        );

        //Seems to be necessary, when simply making a reservation, the RoomReservationList does
        // not update therefore new reservation does not register in the classroom
        classroom.addReservationToList(roomReservation);
        RoomReservationController myReservationController = new RoomReservationController(
                roomRep, userRep, classRep, timeRep);
        assertEquals(1, myReservationController.getAllAvailableTimeslots(
                reservationRequestModel).size(), "Expected 1 available timeslot");
        assertEquals(timeslot2, myReservationController.getAllAvailableTimeslots(
                reservationRequestModel).get(0), "incorrect timeslot");
    }

    @Test
    public void getAllAvailableTimeSlotTestAllBusy() {
        buildingRep.save(testbuilding);
        classRep.save(classroom);
        userRep.save(user);
        timeRep.save(timeslot);
        roomRep.save(roomReservation);

        //This needs to be here because classroomID gets generated after its saved to a repository
        ReservationRequestModel reservationRequestModel = new ReservationRequestModel(
                classroom.getId(),
                "testDate",
                "testTimeslot",
                "testName"
        );

        //Seems to be necessary, when simply making a reservation, the RoomReservationList does
        //not update therefore new reservation does not register in the classroom
        classroom.addReservationToList(roomReservation);
        RoomReservationController myReservationController = new RoomReservationController(
                roomRep, userRep, classRep, timeRep);
        assertEquals(0, myReservationController.getAllAvailableTimeslots(reservationRequestModel)
                .size(), "Expected no available timeslots");
    }

    @Test
    public void getAllAvailableTimeSlotTestAllFree() {
        buildingRep.save(testbuilding);
        classRep.save(classroom);
        userRep.save(user);
        timeRep.save(timeslot);

        //This needs to be here because classroomID gets generated after its saved to a repository
        ReservationRequestModel reservationRequestModel = new ReservationRequestModel(
                classroom.getId(),
                "testDate",
                "testTimeslot",
                "testName"
        );

        RoomReservationController myReservationController = new
                RoomReservationController(roomRep, userRep, classRep, timeRep);
        assertEquals(1, myReservationController.getAllAvailableTimeslots(
                reservationRequestModel).size(), "Expected 1 available timeslot");
        assertEquals(timeslot, myReservationController.getAllAvailableTimeslots(
                reservationRequestModel).get(0), "Expected timeslot");
    }

    /**
     * SendReservations testing.
     */
    @Test
    public void sendReservationsTest() {
        buildingRep.save(testbuilding);
        classRep.save(classroom);
        userRep.save(user);
        timeRep.save(timeslot);
        timeRep.save(timeslot2);
        roomRep.save(roomReservation);
        roomRep.save(roomReservation2);

        RoomReservationController myReservationController = new RoomReservationController(
                roomRep, userRep, classRep, timeRep);
        List<RoomReservation> expected = new ArrayList<RoomReservation>();
        expected.add(roomReservation);
        expected.add(roomReservation2);
        Object actual = myReservationController.sendReservations();
        assertNotNull(actual, "Expected not null");
        assertTrue(actual instanceof List, "Expected a list");
        assertEquals(expected, actual, "Expected a list of reservations");
    }

    @Test
    public void sendReservationsTestEmpty() {
        RoomReservationController myReservationController = new RoomReservationController(
                roomRep, userRep, classRep, timeRep);
        Object actual = myReservationController.sendReservations();
        assertNotNull(actual, "Expected not null");
        assertTrue(actual instanceof String, "Expected a String");
        assertEquals("No reservations Found!", actual, "Expected an error message");
    }


    /**
     * DeleteReservation testing.
     */

    @Test
    public void deleteReservationTest() {
        buildingRep.save(testbuilding);
        classRep.save(classroom);
        userRep.save(user);
        timeRep.save(timeslot);
        roomRep.save(roomReservation);

        RoomReservationController myReservationController = new RoomReservationController(
                roomRep, userRep, classRep, timeRep);
        myReservationController.deleteReservation(roomReservation.getId());
        assertEquals(0, roomRep.findAll().size(), "Expected empty repository");
    }

    @Test
    public void deleteReservationTestManyReservations() {
        buildingRep.save(testbuilding);
        classRep.save(classroom);
        userRep.save(user);
        timeRep.save(timeslot);
        timeRep.save(timeslot2);
        roomRep.save(roomReservation);
        roomRep.save(roomReservation2);

        RoomReservationController myReservationController = new RoomReservationController(
                roomRep, userRep, classRep, timeRep);
        myReservationController.deleteReservation(roomReservation.getId());
        assertEquals(1, roomRep.findAll().size(), "Expected one reservation in repository");
        assertEquals(roomReservation2, roomRep.findAll().get(0), "Wrong reservation deleted");
    }

    @Test
    public void deleteReservationTestMissingReservation() {
        RoomReservationController myReservationController = new RoomReservationController(
                roomRep, userRep, classRep, timeRep);
        myReservationController.deleteReservation(1234L);
        //no assert, should pass with no exception
    }

    @Test
    public void sendReservationsByUserTest() {
        buildingRep.save(testbuilding);
        classRep.save(classroom);
        userRep.save(user);
        timeRep.save(timeslot);
        roomRep.save(roomReservation);

        RoomReservationController myReservationController = new RoomReservationController(
                roomRep, userRep, classRep, timeRep);
        List<RoomReservation> expected = new ArrayList<RoomReservation>();
        expected.add(roomReservation);
        assertEquals(expected, myReservationController.sendReservationsPerUser(user.getUserName()));
    }



}
