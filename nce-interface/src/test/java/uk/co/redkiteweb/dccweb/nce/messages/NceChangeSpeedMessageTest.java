package uk.co.redkiteweb.dccweb.nce.messages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ChangeSpeedMessage;
import uk.co.redkiteweb.dccweb.nce.communication.NceData;
import uk.co.redkiteweb.dccweb.nce.communication.TalkToNCE;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 21/09/16.
 */
@RunWith(JUnit4.class)
public class NceChangeSpeedMessageTest {

    private NceChangeSpeedMessage nceChangeSpeedMessage;
    private NceData nceData;

    @Before
    public void setup() throws ConnectionException {
        nceData = mock(NceData.class);
        final TalkToNCE talkToNCE = mock(TalkToNCE.class);
        nceChangeSpeedMessage = new NceChangeSpeedMessage();
        nceChangeSpeedMessage.setTalkToNCE(talkToNCE);
        when(talkToNCE.sendData(any(NceData.class))).thenReturn(nceData);
        when(nceData.size()).thenReturn(1);
    }

    @Test
    public void testUpSpeed() throws ConnectionException {
        when(nceData.readData()).thenReturn(0x21);
        nceChangeSpeedMessage.process(getChangeSpeedMessage());
    }

    @Test
    public void testDownSpeed() throws ConnectionException {
        when(nceData.readData()).thenReturn(0x21);
        final ChangeSpeedMessage message = getChangeSpeedMessage();
        message.setDirection(ChangeSpeedMessage.Direction.DOWN);
        nceChangeSpeedMessage.process(message);
    }

    @Test
    public void testUpSpeed28() throws ConnectionException {
        when(nceData.readData()).thenReturn(0x21);
        final ChangeSpeedMessage message = getChangeSpeedMessage();
        message.setSpeedSteps(ChangeSpeedMessage.SpeedSteps.STEPS_28);
        nceChangeSpeedMessage.process(message);
    }

    @Test
    public void testDownSpeed28() throws ConnectionException {
        when(nceData.readData()).thenReturn(0x21);
        final ChangeSpeedMessage message = getChangeSpeedMessage();
        message.setDirection(ChangeSpeedMessage.Direction.DOWN);
        message.setSpeedSteps(ChangeSpeedMessage.SpeedSteps.STEPS_28);
        nceChangeSpeedMessage.process(message);
    }

    @Test
    public void testRStop() throws ConnectionException {
        when(nceData.readData()).thenReturn(0x21);
        final ChangeSpeedMessage message = getChangeSpeedMessage();
        message.setDirection(ChangeSpeedMessage.Direction.RSTOP);
        message.setSpeed(0);
        nceChangeSpeedMessage.process(message);
    }

    @Test
    public void testFStop() throws ConnectionException {
        when(nceData.readData()).thenReturn(0x21);
        final ChangeSpeedMessage message = getChangeSpeedMessage();
        message.setDirection(ChangeSpeedMessage.Direction.FSTOP);
        message.setSpeed(0);
        nceChangeSpeedMessage.process(message);
    }

    @Test
    public void testLowAddressUpSpeed() throws ConnectionException {
        when(nceData.readData()).thenReturn(0x21);
        final ChangeSpeedMessage changeSpeedMessage = getChangeSpeedMessage();
        changeSpeedMessage.setAddress(16);
        changeSpeedMessage.setAddressMode(false);
        nceChangeSpeedMessage.process(changeSpeedMessage);
    }

    private ChangeSpeedMessage getChangeSpeedMessage() {
        final ChangeSpeedMessage message = new ChangeSpeedMessage();
        message.setAddress(1234);
        message.setAddressMode(true);
        message.setSpeed(50);
        message.setSpeedSteps(ChangeSpeedMessage.SpeedSteps.STEPS_128);
        message.setDirection(ChangeSpeedMessage.Direction.UP);
        return message;
    }
}
