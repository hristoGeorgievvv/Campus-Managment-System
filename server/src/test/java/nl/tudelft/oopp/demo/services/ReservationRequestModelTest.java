package nl.tudelft.oopp.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ReservationRequestModelTest {

    ReservationRequestModel model1 = new ReservationRequestModel((long) 5, "TestDate",
            "TestTimeSlot", "TestName");
    ReservationRequestModel model2 = new ReservationRequestModel((long) 5, "TestDate",
            "TestTimeSlot", "TestName");

    @Test
    public void constructorTest() {
        assertEquals(5, model1.getRoomId(), "RoomId constructor not working properly");
        assertEquals("TestDate", model1.getDate(), "date constructor not working properly");
        assertEquals("TestTimeSlot", model1.getTimeSlot(),
                "TimeSlot constructor not working properly");
        assertEquals("TestName", model1.getUserName(), "Name constructor not working properly");
    }

    @Test
    public void getIdTest() {
        assertEquals(5, model1.getRoomId(), "getRoomId not working properly");
    }

    @Test
    public void getDateTest() {
        assertEquals("TestDate", model1.getDate(), "getDate not working properly");
    }

    @Test
    public void getTimeSlotTest() {
        assertEquals("TestTimeSlot", model1.getTimeSlot(), "getTimeSlot not working properly");
    }

    @Test
    public void equalsSameTest() {
        assertTrue(model1.equals(model1), "ReservationRequestModels should be equal to itself");
    }

    @Test
    public void equalsSameAttributesTest() {
        assertTrue(model1.equals(model2),
                "ReservationRequestModels should be equal when attributes are equal");
    }

    @Test
    public void equalsDifferentIdTest() {
        model1 = new ReservationRequestModel((long) 2,"TestDate",
                "TestTimeSlot", "TestName");
        assertFalse(model1.equals(model2), "should not be equal when id is not equal");
    }

    @Test
    public void equalsDifferentDateTest() {
        model1 = new ReservationRequestModel((long) 5,"Diff",
                "TestTimeSlot", "TestName");
        assertFalse(model1.equals(model2), "should not be equal when date is not equal");
    }

    @Test
    public void equalsDifferentTimeSlotTest() {
        model1 = new ReservationRequestModel((long) 5,"TestDate",
                "Diff", "TestName");
        assertFalse(model1.equals(model2), "should not be equal when timeSlot is not equal");
    }

    @Test
    public void equalsDifferentUserNameTest() {
        model1 = new ReservationRequestModel((long) 5,"TestDate",
                "TestTimeSlot", "Diff");
        assertFalse(model1.equals(model2), "should not be equal when userName is not equal");
    }
}
