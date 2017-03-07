package uk.co.redkiteweb.dccweb.demo.messages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ReadCVMessage;
import uk.co.redkiteweb.dccweb.demo.registers.DecoderRegister;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 25/07/16.
 */
@RunWith(JUnit4.class)
public class DemoReadCVMessageTest {

    private DemoReadCVMessage demoReadCVMessage;
    private DecoderRegister decoderRegister;

    @Before
    public void setUp() {
        decoderRegister = mock(DecoderRegister.class);
        demoReadCVMessage = new DemoReadCVMessage();
        demoReadCVMessage.setDecoderRegister(decoderRegister);
    }

    @Test
    public void test() {
        when(decoderRegister.getCV(eq(8))).thenReturn(1);
        final ReadCVMessage message = new ReadCVMessage();
        message.setCvReg(8);
        final MessageResponse messageResponse = demoReadCVMessage.process(message);
        assertEquals(1, messageResponse.get("CVData"));
    }

}
