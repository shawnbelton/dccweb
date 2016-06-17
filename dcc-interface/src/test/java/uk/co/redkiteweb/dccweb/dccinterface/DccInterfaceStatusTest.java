package uk.co.redkiteweb.dccweb.dccinterface;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 17/06/16.
 */
@RunWith(JUnit4.class)
public class DccInterfaceStatusTest {

    private DccInterfaceStatus dccInterfaceStatus;

    @Before
    public void setUp() {
        dccInterfaceStatus = new DccInterfaceStatus();
    }

    @Test
    public void connectedTest() {
        dccInterfaceStatus.setConnected();
        assertEquals(DccInterfaceStatus.Status.CONNECTED, dccInterfaceStatus.getStatus());
    }

    @Test
    public void disconnectedTest() {
        dccInterfaceStatus.setDisconnected();
        assertEquals(DccInterfaceStatus.Status.DISCONNECTED, dccInterfaceStatus.getStatus());
    }

    @Test
    public void offLineTest() {
        dccInterfaceStatus.setOffLine();
        assertEquals(DccInterfaceStatus.Status.OFF_LINE, dccInterfaceStatus.getStatus());
    }

}
