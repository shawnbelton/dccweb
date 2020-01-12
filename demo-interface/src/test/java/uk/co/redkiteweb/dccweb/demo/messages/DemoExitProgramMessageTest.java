package uk.co.redkiteweb.dccweb.demo.messages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ExitProgramMessage;

import static org.junit.Assert.assertTrue;

/**
 * Created by shawn on 25/07/16.
 */
@RunWith(JUnit4.class)
public class DemoExitProgramMessageTest {

    private DemoExitProgramMessage demoExitProgramMessage;

    @Before
    public void setUp() {
        demoExitProgramMessage = new DemoExitProgramMessage();
    }

    @Test
    public void keepAliveTest() {
        demoExitProgramMessage.process(new ExitProgramMessage());
        assertTrue(true);
    }

}
