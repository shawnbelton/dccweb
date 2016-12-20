package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterfaceStatus;

import static org.mockito.Mockito.*;

/**
 * Created by shawn on 17/06/16.
 */
@RunWith(JUnit4.class)
public class InterfaceInfoTest {

    private InterfaceInfo interfaceInfo;
    private DccInterface dccInterface;
    private DccInterfaceStatus dccInterfaceStatus;

    @Before
    public void setUp() {
        dccInterface = mock(DccInterface.class);
        dccInterfaceStatus = mock(DccInterfaceStatus.class);
        interfaceInfo = new InterfaceInfo();
        interfaceInfo.setDccInterface(dccInterface);
    }

    @Test
    public void testStatus() {
        interfaceInfo.getStatus();
        verify(dccInterface, times(1)).getInterfaceStatus();
    }

    @Test
    public void testInterfaces() {
        interfaceInfo.getInterfaces();
        verify(dccInterface, times(1)).getInterfaces();
    }
}
