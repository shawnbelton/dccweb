package uk.co.redkiteweb.dccweb.nce.messages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.nce.communication.NceData;
import uk.co.redkiteweb.dccweb.nce.communication.TalkToNCE;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 09/07/16.
 */
@RunWith(JUnit4.class)
public class NceKeepAliveMessageTest {

    private NceKeepAliveMessage nceKeepAliveMessage;
    private TalkToNCE talkToNCE;

    @Before
    public void setUp() {
        talkToNCE = mock(TalkToNCE.class);
        nceKeepAliveMessage = new NceKeepAliveMessage();
        nceKeepAliveMessage.setTalkToNCE(talkToNCE);
    }

    @Test
    public void connectionOKTest() throws ConnectionException {
        final NceData nceData = new NceData();
        nceData.addData('!');
        when(talkToNCE.sendData(any(NceData.class))).thenReturn(nceData);
        final MessageResponse messageResponse = nceKeepAliveMessage.process(mock(Message.class));
        verify(talkToNCE, times(1)).sendData(any(NceData.class));
        assertEquals(MessageResponse.MessageStatus.OK, messageResponse.getStatus());
    }

    @Test
    public void connectionNullValue() throws ConnectionException {
        final NceData nceData = new NceData();
        when(talkToNCE.sendData(any(NceData.class))).thenReturn(nceData);
        final MessageResponse messageResponse = nceKeepAliveMessage.process(mock(Message.class));
        verify(talkToNCE, times(1)).sendData(any(NceData.class));
        assertEquals(MessageResponse.MessageStatus.ERROR, messageResponse.getStatus());
    }

    @Test
    public void connectionErrorValue() throws ConnectionException {
        final NceData nceData = new NceData();
        nceData.addData('3');
        when(talkToNCE.sendData(any(NceData.class))).thenReturn(nceData);
        final MessageResponse messageResponse = nceKeepAliveMessage.process(mock(Message.class));
        verify(talkToNCE, times(1)).sendData(any(NceData.class));
        assertEquals(MessageResponse.MessageStatus.ERROR, messageResponse.getStatus());
    }

    @Test
    public void connectionException() throws ConnectionException {
        when(talkToNCE.sendData(any(NceData.class))).thenThrow(new ConnectionException("Test"));
        try {
            nceKeepAliveMessage.process(mock(Message.class));
        } catch (ConnectionException exception) {

        }
        verify(talkToNCE, times(1)).shutdown();
    }
}
