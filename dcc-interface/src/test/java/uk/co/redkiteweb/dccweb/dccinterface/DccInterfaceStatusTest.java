package uk.co.redkiteweb.dccweb.dccinterface;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.service.NotificationService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 17/06/16.
 */
@RunWith(JUnit4.class)
public class DccInterfaceStatusTest {

    private DccInterfaceStatus dccInterfaceStatus;
    private NotificationService notificationService;

    @Before
    public void setUp() {
        notificationService = mock(NotificationService.class);
        dccInterfaceStatus = new DccInterfaceStatus();
        dccInterfaceStatus.setNotificationService(notificationService);
    }

    @Test
    public void connectedTest() {
        dccInterfaceStatus.setConnected();
        assertEquals(DccInterfaceStatus.Status.CONNECTED, dccInterfaceStatus.getStatus());
        verify(notificationService, times(1)).createNotification(anyString(), anyString());
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
