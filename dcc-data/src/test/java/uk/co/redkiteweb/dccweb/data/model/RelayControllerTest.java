package uk.co.redkiteweb.dccweb.data.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 31/05/17.
 */
@RunWith(JUnit4.class)
public class RelayControllerTest {

    private RelayController relayController;

    @Before
    public void setup() {
        relayController = new RelayController();
        relayController.setControllerId("ControllerId");
        relayController.setIpAddress("192.168.1.1");
        relayController.setControllerName("Controller Name");
        relayController.setValue(128);
    }

    @Test
    public void testId() {
        assertEquals("ControllerId", relayController.getControllerId());
    }

    @Test
    public void testIpAddress() {
        assertEquals("192.168.1.1", relayController.getIpAddress());
    }

    @Test
    public void testName() {
        assertEquals("Controller Name", relayController.getControllerName());
    }

    @Test
    public void testValue() {
        assertEquals(new Integer(128), relayController.getValue());
    }
}
