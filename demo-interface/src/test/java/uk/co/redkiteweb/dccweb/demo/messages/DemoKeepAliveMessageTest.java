package uk.co.redkiteweb.dccweb.demo.messages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.dccinterface.messages.KeepAliveMessage;

import static org.junit.Assert.assertTrue;

/**
 * Created by shawn on 25/07/16.
 */
@RunWith(JUnit4.class)
public class DemoKeepAliveMessageTest {

    private DemoKeepAliveMessage demoKeepAliveMessage;

    @Before
    public void setUp() {
        demoKeepAliveMessage = new DemoKeepAliveMessage();
    }

    @Test
    public void keepAliveTest() {
        demoKeepAliveMessage.process(new KeepAliveMessage());
        assertTrue(true);
    }

}
