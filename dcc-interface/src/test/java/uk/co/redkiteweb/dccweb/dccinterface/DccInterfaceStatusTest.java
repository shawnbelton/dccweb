package uk.co.redkiteweb.dccweb.dccinterface;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 17/06/16.
 */
@RunWith(JUnit4.class)
public class DccInterfaceStatusTest {

    private DccInterfaceStatus dccInterfaceStatus;
    private SimpMessagingTemplate simpMessagingTemplate;

    @Before
    public void setUp() {
        simpMessagingTemplate = mock(SimpMessagingTemplate.class);
        dccInterfaceStatus = new DccInterfaceStatus();
        dccInterfaceStatus.setMessagingTemplate(simpMessagingTemplate);
    }

    @Test
    public void connectedTest() {
        dccInterfaceStatus.setConnected();
        assertEquals(DccInterfaceStatus.Status.CONNECTED, dccInterfaceStatus.getStatus());
        verify(simpMessagingTemplate, times(1)).convertAndSend(eq("/status"), anyString());
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
