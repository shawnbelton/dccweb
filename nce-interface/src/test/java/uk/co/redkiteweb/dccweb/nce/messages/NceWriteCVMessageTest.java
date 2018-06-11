package uk.co.redkiteweb.dccweb.nce.messages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.CV;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.dccinterface.messages.WriteCVMessage;
import uk.co.redkiteweb.dccweb.nce.communication.NceData;
import uk.co.redkiteweb.dccweb.nce.communication.TalkToNCE;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class NceWriteCVMessageTest {

    private NceWriteCVMessage nceWriteCVMessage;
    private TalkToNCE talkToNCE;

    @Before
    public void setUp() {
        talkToNCE = mock(TalkToNCE.class);
        nceWriteCVMessage = new NceWriteCVMessage();
        nceWriteCVMessage.setTalkToNCE(talkToNCE);
    }

    @Test
    public void testWriteCV() throws ConnectionException {
        assertEquals(MessageResponse.MessageStatus.OK, testWrite());
    }

    private MessageResponse.MessageStatus testWrite() throws ConnectionException {
        final NceData nceData = mock(NceData.class);
        when(talkToNCE.sendData(any(NceData.class))).thenReturn(nceData);
        when(nceData.readData()).thenReturn(33);
        final WriteCVMessage writeCVMessage = new WriteCVMessage();
        final CV cv = new CV();
        cv.setCvNumber(1234);
        cv.setCvValue(123);
        writeCVMessage.setCv(cv);
        return nceWriteCVMessage.process(writeCVMessage).getStatus();
    }

}
