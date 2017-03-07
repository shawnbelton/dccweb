package uk.co.redkiteweb.dccweb.nce.messages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.dccinterface.messages.OperateAccessoryMessage;
import uk.co.redkiteweb.dccweb.nce.communication.NceData;
import uk.co.redkiteweb.dccweb.nce.communication.TalkToNCE;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 07/03/17.
 */
@RunWith(JUnit4.class)
public class NceOperateAccessoryMessageTest {

    private NceOperateAccessoryMessage nceOperateAccessoryMessage;
    private TalkToNCE talkToNCE;

    @Before
    public void setup() {
        talkToNCE = mock(TalkToNCE.class);
        nceOperateAccessoryMessage = new NceOperateAccessoryMessage();
        nceOperateAccessoryMessage.setTalkToNCE(talkToNCE);
    }

    @Test
    public void testProcess() throws ConnectionException {
        final NceData data = mock(NceData.class);
        when(talkToNCE.sendData(any(NceData.class))).thenReturn(data);
        when(data.size()).thenReturn(1);
        when(data.readData()).thenReturn(0x21);
        final OperateAccessoryMessage message = new OperateAccessoryMessage();
        message.setAccessoryOperation(1);
        message.setAccessoryAddress(123);
        final MessageResponse messageResponse = nceOperateAccessoryMessage.process(message);
        assertEquals(MessageResponse.MessageStatus.OK, messageResponse.getStatus());
    }
}
