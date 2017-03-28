package uk.co.redkiteweb.dccweb.dccinterface.messages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 07/03/17.
 */
@RunWith(JUnit4.class)
public class OperateAccessoryMessageTest {

    private OperateAccessoryMessage message;

    @Before
    public void setup() {
        message = new OperateAccessoryMessage();
        message.setAccessoryAddress(123);
        message.setAccessoryOperation(1);
        message.setLogMessage("Setting accessory 123 to 1");
    }

    @Test
    public void testAccessoryAddress() {
        assertEquals(new Integer(123), message.getAccessoryAddress());
    }

    @Test
    public void testAccessoryOperation() {
        assertEquals(new Integer(1), message.getAccessoryOperation());
    }

    @Test
    public void testLogMessage() {
        assertEquals("Setting accessory 123 to 1", message.getLogMessage());
    }
}
