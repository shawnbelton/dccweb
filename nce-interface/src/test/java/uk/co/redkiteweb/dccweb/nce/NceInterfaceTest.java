package uk.co.redkiteweb.dccweb.nce;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterfaceStatus;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

/**
 * Created by shawn on 15/06/16.
 */
@RunWith(JUnit4.class)
public class NceInterfaceTest {

    private NceInterface nceInterface;
    private DccInterfaceStatus dccInterfaceStatus;

    @Before
    public void setUp() {
        dccInterfaceStatus = mock(DccInterfaceStatus.class);
        this.nceInterface = new NceInterface();
        nceInterface.setDccInterfaceStatus(dccInterfaceStatus);
    }

    @Test
    public void initialise() {
        nceInterface.initialise();
        verify(dccInterfaceStatus, times(1)).setDisconnected();
    }
}
