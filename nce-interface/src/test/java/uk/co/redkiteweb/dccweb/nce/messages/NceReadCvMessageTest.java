package uk.co.redkiteweb.dccweb.nce.messages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ReadCVMessage;
import uk.co.redkiteweb.dccweb.nce.communication.NceData;
import uk.co.redkiteweb.dccweb.nce.communication.TalkToNCE;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 11/07/16.
 */
@RunWith(JUnit4.class)
public class NceReadCvMessageTest {

    private NceReadCVMessage nceReadCVMessage;
    private TalkToNCE talkToNCE;

    @Before
    public void setUp() {
        talkToNCE = mock(TalkToNCE.class);
        nceReadCVMessage = new NceReadCVMessage();
        nceReadCVMessage.setTalkToNCE(talkToNCE);
    }

    @Test
    public void readCVTest() throws ConnectionException {
        final NceData nceData = mock(NceData.class);
        when(talkToNCE.sendData(any(NceData.class))).thenReturn(nceData);
        when(nceData.size()).thenReturn(2);
        when(nceData.readData()).thenReturn(33).thenReturn(33);
        final ReadCVMessage readCVMessage = new ReadCVMessage();
        readCVMessage.setCvReg(8);
        final MessageResponse messageResponse = nceReadCVMessage.process(readCVMessage);
        assertEquals(MessageResponse.MessageStatus.OK, messageResponse.getStatus());
    }

    @Test
    public void readCVFailTest() throws ConnectionException {
        final NceData nceData = mock(NceData.class);
        when(talkToNCE.sendData(any(NceData.class))).thenReturn(nceData);
        when(nceData.size()).thenReturn(2);
        when(nceData.readData()).thenReturn(255).thenReturn(51);
        final ReadCVMessage readCVMessage = new ReadCVMessage();
        readCVMessage.setCvReg(8);
        final MessageResponse messageResponse = nceReadCVMessage.process(readCVMessage);
        assertEquals(MessageResponse.MessageStatus.ERROR, messageResponse.getStatus());
    }

    @Test
    public void readCVNotProgramTest() throws ConnectionException {
        final NceData nceData = mock(NceData.class);
        when(talkToNCE.sendData(any(NceData.class))).thenReturn(nceData);
        when(nceData.size()).thenReturn(1);
        when(nceData.readData()).thenReturn(48);
        final ReadCVMessage readCVMessage = new ReadCVMessage();
        readCVMessage.setCvReg(8);
        final MessageResponse messageResponse = nceReadCVMessage.process(readCVMessage);
        assertEquals(MessageResponse.MessageStatus.ERROR, messageResponse.getStatus());
    }

    @Test
    public void readCVErrorRouteTest() throws ConnectionException {
        final NceData nceData = mock(NceData.class);
        when(talkToNCE.sendData(any(NceData.class))).thenReturn(nceData);
        when(nceData.size()).thenReturn(1);
        when(nceData.readData()).thenReturn(33);
        final ReadCVMessage readCVMessage = new ReadCVMessage();
        readCVMessage.setCvReg(8);
        final MessageResponse messageResponse = nceReadCVMessage.process(readCVMessage);
        assertEquals(MessageResponse.MessageStatus.OK, messageResponse.getStatus());
    }

}
