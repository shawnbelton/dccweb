package uk.co.redkiteweb.dccweb.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class RelayContollerTest {

    private RelayController relayController;

    @Before
    public void setup() {
        relayController = new RelayController();
        relayController.setControllerId("controllerId");
        relayController.setControllerName("controllerName");
        relayController.setIpAddress("ipAddress");
        relayController.setValue(123);
    }

    @Test
    public void testControllerId() {
        assertEquals("controllerId", relayController.getControllerId());
    }

    @Test
    public void testControllerName() {
        assertEquals("controllerName", relayController.getControllerName());
    }

    @Test
    public void testIpAddress() {
        assertEquals("ipAddress", relayController.getIpAddress());
    }

    @Test
    public void testValue() {
        assertEquals(new Integer(123), relayController.getValue());
    }
}
