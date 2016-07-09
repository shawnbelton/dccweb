package uk.co.redkiteweb.dccweb.nce.messages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.nce.communication.TalkToNCE;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by shawn on 09/07/16.
 */
@RunWith(JUnit4.class)
public class NceShutdownMessageTest {

    private NceShutdownMessage nceShutdownMessage;
    private TalkToNCE talkToNCE;

    @Before
    public void setUp() {
        talkToNCE = mock(TalkToNCE.class);
        nceShutdownMessage = new NceShutdownMessage();
        nceShutdownMessage.setTalkToNCE(talkToNCE);
    }

    @Test
    public void processTest() {
        nceShutdownMessage.process(mock(Message.class));
        verify(talkToNCE, times(1)).shutdown();
    }
}
