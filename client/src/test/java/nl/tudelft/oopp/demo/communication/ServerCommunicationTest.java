package nl.tudelft.oopp.demo.communication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import nl.tudelft.oopp.demo.models.Building;
import org.junit.jupiter.api.Test;



public class ServerCommunicationTest {

    //Tests wont work while server is running
    //just meant to test as much logic as possible

    @Test
    public void getUtilityTest() {
        assertNull(ServerCommunication.getUtility(
                "testmap"
        ), "Method should return null while server is offline");
    }

    @Test
    public void getUtilityTest2() {
        assertNull(ServerCommunication.getUtility(
                "testmap",
                "TestUsername",
                "TestPassword"
        ), "Method should return null while server is offline");
    }

    @Test
    public void deleteUtilityTest() {
        assertNull(ServerCommunication.deleteUtility(
                "testmap"
        ), "Method should return null while server is offline");
    }

    @Test
    public void postUtilityTest() {
        Building testbuilding = new Building(
            "Hours",
            "Buildingname",
            "Dept",
            "Delft",
            15
        );
        assertNull(ServerCommunication.postUtility(
                "testmap",
                testbuilding
        ), "Method should return null while server is offline");
    }

    @Test
    public void testGetClassrooms() {
        assertEquals("Communication with server failed",
                ServerCommunication.getClassrooms("testmap"),
                "Server fail messages should match");
    }

    @Test
    public void updateUtilityTest() {
        Building testbuilding = new Building(
                "Hours",
                "Buildingname",
                "Dept",
                "Delft",
                15
        );
        assertNull(ServerCommunication.updateUtility(
                "testmap",
                testbuilding
        ), "Method should return null while server is offline");
    }
}

