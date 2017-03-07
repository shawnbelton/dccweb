package uk.co.redkiteweb.dccweb.demo.messages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.dccinterface.messages.OperateAccessoryMessage;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 07/03/17.
 */
@RunWith(JUnit4.class)
public class DemoOperateAccessoryMessageTest {

    private DemoOperateAccessoryMessage demoOperateAccessoryMessage;

    @Before
    public void setup() {
        demoOperateAccessoryMessage = new DemoOperateAccessoryMessage();
    }

    @Test
    public void testProcess() {
        final OperateAccessoryMessage message = new OperateAccessoryMessage();
        message.setAccessoryAddress(123);
        message.setAccessoryOperation(1);
        assertEquals(MessageResponse.MessageStatus.OK, demoOperateAccessoryMessage.process(message).getStatus());
    }
}
