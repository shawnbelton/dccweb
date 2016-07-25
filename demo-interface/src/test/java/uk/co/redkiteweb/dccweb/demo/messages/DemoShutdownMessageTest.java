package uk.co.redkiteweb.dccweb.demo.messages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ShutdownMessage;

/**
 * Created by shawn on 25/07/16.
 */
@RunWith(JUnit4.class)
public class DemoShutdownMessageTest {

    private DemoShutdownMessage demoShutdownMessage;

    @Before
    public void setUp() {
        demoShutdownMessage = new DemoShutdownMessage();
    }

    @Test
    public void shutdownTest() {
        demoShutdownMessage.process(new ShutdownMessage());
    }

}
