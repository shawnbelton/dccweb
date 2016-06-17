package uk.co.redkiteweb.dccweb.dccinterface;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 17/06/16.
 */
@RunWith(JUnit4.class)
public class DccInterfaceTest {

    private AbstractDccInterface abstractDccInterface;
    private DccInterfaceStatus dccInterfaceStatus;

    @Before
    public void setUp() {
        dccInterfaceStatus = mock(DccInterfaceStatus.class);
        abstractDccInterface = new MyDccInterface();
        abstractDccInterface.setDccInterfaceStatus(dccInterfaceStatus);
    }

    @Test
    public void testInitialise() {
        abstractDccInterface.initialise();
        verify(dccInterfaceStatus, times(1)).setDisconnected();
    }

    @Test
    public void testStatus() {
        when(dccInterfaceStatus.getStatus()).thenReturn(DccInterfaceStatus.Status.DISCONNECTED);
        assertEquals(DccInterfaceStatus.Status.DISCONNECTED, abstractDccInterface.getInterfaceStatus());
    }

    @Test
    public void testGetDccInterfaceStatus() {
        assertTrue(abstractDccInterface.getDccInterfaceStatus() instanceof DccInterfaceStatus);
    }

}
