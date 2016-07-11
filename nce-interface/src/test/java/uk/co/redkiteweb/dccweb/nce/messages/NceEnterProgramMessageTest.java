package uk.co.redkiteweb.dccweb.nce.messages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.dccinterface.messages.EnterProgramMessage;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
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
public class NceEnterProgramMessageTest {

    private NceEnterProgramMessage nceEnterProgramMessage;
    private TalkToNCE talkToNCE;

    @Before
    public void setUp() {
        talkToNCE = mock(TalkToNCE.class);
        nceEnterProgramMessage = new NceEnterProgramMessage();
        nceEnterProgramMessage.setTalkToNCE(talkToNCE);
    }

    @Test
    public void testFailEnter() throws ConnectionException {
        assertEquals(MessageResponse.MessageStatus.ERROR, testEnter(51));
    }

    @Test
    public void testFailOnNull() throws ConnectionException {
        assertEquals(MessageResponse.MessageStatus.ERROR, testEnter(null));
    }

    @Test
    public void testFailOK() throws ConnectionException {
        assertEquals(MessageResponse.MessageStatus.OK, testEnter(33));
    }

    private MessageResponse.MessageStatus testEnter(final Integer value) throws ConnectionException {
        final NceData nceData = mock(NceData.class);
        when(talkToNCE.sendData(any(NceData.class))).thenReturn(nceData);
        when(nceData.readData()).thenReturn(value);
        return nceEnterProgramMessage.process(new EnterProgramMessage()).getStatus();
    }

}
