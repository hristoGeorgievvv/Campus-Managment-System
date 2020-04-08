package nl.tudelft.oopp.demo.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import nl.tudelft.oopp.demo.models.TimeSlots;
import org.junit.jupiter.api.Test;

public class TimeSlotsTest {

    private TimeSlots timeslot = new TimeSlots(123L,"testDuration");

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
        assertEquals(123L, timeslot.getId(), "Id not properly Saved");
        assertEquals("testDuration", timeslot.getTimeDuration(), "timeDuration not properly saved");
    }

    @Test
    public void getTimeDurationTest() {
        assertEquals("testDuration", timeslot.getTimeDuration(), "getTimeDuration incorrect");
    }

    @Test
    public void getIdTest() {
        assertEquals(123L, timeslot.getId(), "getID incorrect");
    }

    /**
     * equals testing.
     */

    @Test
    public void equalsTest() {
        TimeSlots timeslot2 = new TimeSlots(123L,"testDuration");
        assertTrue(timeslot.equals(timeslot2), "equal timeslots should be equal");
    }

    @Test
    public void equalsTestSame() {
        assertTrue(timeslot.equals(timeslot),
                "same timeslot should be equal");
    }

    @Test
    public void equalsTestNotEqual() {
        TimeSlots timeslot2 = new TimeSlots(321L,"testDuration");
        assertFalse(timeslot.equals(timeslot2),
                "time slots with different Ids should not be equal");
    }
}
