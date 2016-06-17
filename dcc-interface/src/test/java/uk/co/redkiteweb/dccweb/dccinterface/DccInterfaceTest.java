package uk.co.redkiteweb.dccweb.dccinterface;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
    public void testStatus() {
        when(dccInterfaceStatus.getStatus()).thenReturn(DccInterfaceStatus.Status.DISCONNECTED);
        assertEquals(DccInterfaceStatus.Status.DISCONNECTED, abstractDccInterface.getInterfaceStatus());
    }


}
