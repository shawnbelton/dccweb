package uk.co.redkiteweb.dccweb.demo.messages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.dccinterface.messages.EnterProgramMessage;

/**
 * Created by shawn on 25/07/16.
 */
@RunWith(JUnit4.class)
public class DemoEnterProgramMessageTest {

    private DemoEnterProgramMessage demoEnterProgramMessage;

    @Before
    public void setUp() {
        demoEnterProgramMessage = new DemoEnterProgramMessage();
    }

    @Test
    public void keepAliveTest() {
        demoEnterProgramMessage.process(new EnterProgramMessage());
    }

}
