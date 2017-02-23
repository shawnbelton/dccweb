package uk.co.redkiteweb.dccweb.demo.messages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.dccinterface.messages.UpdateFunctionsMessage;

/**
 * Created by shawn on 25/07/16.
 */
@RunWith(JUnit4.class)
public class DemoUpdateFunctionsMessageTest {

    private DemoUpdateFunctionsMessage demoUpdateFunctionsMessage;

    @Before
    public void setUp() {
        demoUpdateFunctionsMessage = new DemoUpdateFunctionsMessage();
    }

    @Test
    public void keepAliveTest() {
        demoUpdateFunctionsMessage.process(new UpdateFunctionsMessage());
    }

}
