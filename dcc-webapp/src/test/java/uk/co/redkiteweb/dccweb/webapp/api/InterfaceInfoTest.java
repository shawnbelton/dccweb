package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterfaceStatus;
import uk.co.redkiteweb.dccweb.factories.DccInterfaceFactory;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 17/06/16.
 */
@RunWith(JUnit4.class)
public class InterfaceInfoTest {

    private InterfaceInfo interfaceInfo;
    private DccInterfaceFactory dccInterfaceFactory;
    private DccInterface dccInterface;
    private DccInterfaceStatus dccInterfaceStatus;

    @Before
    public void setUp() {
        dccInterfaceFactory = mock(DccInterfaceFactory.class);
        dccInterface = mock(DccInterface.class);
        dccInterfaceStatus = mock(DccInterfaceStatus.class);
        interfaceInfo = new InterfaceInfo();
        interfaceInfo.setDccInterfaceFactory(dccInterfaceFactory);
        when(dccInterfaceFactory.getInstance()).thenReturn(dccInterface);
    }

    @Test
    public void testStatus() {
        when(dccInterface.getInterfaceStatus()).thenReturn(dccInterfaceStatus);
        when(dccInterfaceStatus.getStatus()).thenReturn(DccInterfaceStatus.Status.DISCONNECTED);
        assertEquals(DccInterfaceStatus.Status.DISCONNECTED, interfaceInfo.getStatus().getStatus());
    }
}
