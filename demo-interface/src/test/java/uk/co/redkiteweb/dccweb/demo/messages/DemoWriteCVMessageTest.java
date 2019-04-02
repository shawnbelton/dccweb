package uk.co.redkiteweb.dccweb.demo.messages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

@RunWith(JUnit4.class)
public class DemoWriteCVMessageTest {

    private DemoWriteCVMessage demoWriteCVMessage;

    @Before
    public void setup() {
        demoWriteCVMessage = new DemoWriteCVMessage();
    }

    @Test
    public void testWriteCv() {
        demoWriteCVMessage.process(mock(Message.class));
        assertTrue(true);
    }
}
