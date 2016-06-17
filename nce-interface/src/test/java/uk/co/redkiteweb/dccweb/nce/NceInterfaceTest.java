package uk.co.redkiteweb.dccweb.nce;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterfaceStatus;
import uk.co.redkiteweb.dccweb.nce.communication.NceData;
import uk.co.redkiteweb.dccweb.nce.communication.TalkToNCE;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 15/06/16.
 */
@RunWith(JUnit4.class)
public class NceInterfaceTest {

    private NceInterface nceInterface;
    private DccInterfaceStatus dccInterfaceStatus;
    private TalkToNCE talkToNCE;

    @Before
    public void setUp() {
        dccInterfaceStatus = mock(DccInterfaceStatus.class);
        talkToNCE = mock(TalkToNCE.class);
        this.nceInterface = new NceInterface();
        nceInterface.setDccInterfaceStatus(dccInterfaceStatus);
        nceInterface.setTalkToNCE(talkToNCE);
    }

    @Test
    public void initialise() {
        nceInterface.initialise();
        verify(dccInterfaceStatus, times(1)).setDisconnected();
    }

    @Test
    public void connectionTest() throws ConnectionException {
        nceInterface.checkInterface();
        verify(talkToNCE, times(1)).sendData(any(NceData.class));
        verify(dccInterfaceStatus, times(1)).setConnected();
    }

    @Test
    public void failedConnectionTest() throws ConnectionException {
        doThrow(mock(ConnectionException.class)).when(talkToNCE).sendData(any(NceData.class));
        nceInterface.checkInterface();
        verify(dccInterfaceStatus, times(1)).setDisconnected();
    }
}
