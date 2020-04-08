package nl.tudelft.oopp.demo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import nl.tudelft.oopp.demo.repositories.TimeSlotsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class TimeSlotTest {

    @Autowired
    private TimeSlotsRepository timeRep;

    private TimeSlots timeslot = new TimeSlots("testDuration");

    /**
     * Testing.
     */
    @Test
    public void timeSlotConstructorTestEmpty() {
        TimeSlots timeslot = new TimeSlots();
        assertNotNull(timeslot, "Expected not null");
    }

    @Test
    public void timeSlotConstructorTest() {
        assertNotNull(timeslot, "Expected not null");
        assertEquals("testDuration", timeslot.getTimeDuration(), "timeDuration not properly saved");
    }

    @Test
    public void timeSlotGetIdTest() {
        timeRep.save(timeslot);
        assertNotNull(timeslot.getId(), "ID not generated");
    }

    @Test
    public void setIdTest() {
        timeRep.save(timeslot);
        timeslot.setId(123L);
        assertEquals(123L, timeslot.getId(), "setID is not functioning");
    }

    @Test
    public void getTimeDurationTest() {
        assertEquals("testDuration", timeslot.getTimeDuration(), "getTimeDuration incorrect");
    }

    /**
     * equals testing.
     */

    @Test
    public void equalsTest() {
        TimeSlots timeslot2 = new TimeSlots("testDuration");
        assertTrue(timeslot.equals(timeslot2), "equal timeslots should be equal");
    }

    @Test
    public void equalsTestSame() {
        assertTrue(timeslot.equals(timeslot),
                "same timeslot should be equal");
    }

    @Test
    public void equalsTestNotEqual() {
        TimeSlots timeslot2 = new TimeSlots("testDuration2");
        assertFalse(timeslot.equals(timeslot2),
                "time slots with different duration should not be equal");
    }
}
