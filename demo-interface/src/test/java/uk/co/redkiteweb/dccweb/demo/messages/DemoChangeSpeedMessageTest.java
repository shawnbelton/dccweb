package uk.co.redkiteweb.dccweb.demo.messages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ChangeSpeedMessage;

import static org.junit.Assert.assertTrue;

/**
 * Created by shawn on 25/07/16.
 */
@RunWith(JUnit4.class)
public class DemoChangeSpeedMessageTest {

    private DemoChangeSpeedMessage demoChangeSpeedMessage;

    @Before
    public void setUp() {
        demoChangeSpeedMessage = new DemoChangeSpeedMessage();
    }

    @Test
    public void keepAliveTest() {
        demoChangeSpeedMessage.process(new ChangeSpeedMessage());
        assertTrue(true);
    }

}
