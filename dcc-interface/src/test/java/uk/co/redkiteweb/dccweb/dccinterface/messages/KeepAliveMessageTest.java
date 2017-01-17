package uk.co.redkiteweb.dccweb.dccinterface.messages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 17/01/17.
 */
@RunWith(JUnit4.class)
public class KeepAliveMessageTest {

    private KeepAliveMessage keepAliveMessage;

    @Before
    public void setup() {
        keepAliveMessage = new KeepAliveMessage();
    }

    @Test
    public void testMessageText() {
        assertEquals("Keep Alive", keepAliveMessage.getLogMessage());
    }
}

