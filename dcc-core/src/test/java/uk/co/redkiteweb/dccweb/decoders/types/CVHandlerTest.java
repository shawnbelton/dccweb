package uk.co.redkiteweb.dccweb.decoders.types;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.CV;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 18/09/16.
 */
@RunWith(JUnit4.class)
public class CVHandlerTest {

    private CVHandler cvHandler;
    private MessageResponse messageResponse;
    private DccInterface dccInterface;

    @Before
    public void setup() {
        dccInterface = mock(DccInterface.class);
        messageResponse = mock(MessageResponse.class);
        cvHandler = new CVHandler();
        cvHandler.setDccInterface(dccInterface);
        when(dccInterface.sendMessage(any(Message.class))).thenReturn(messageResponse);
    }

    @Test
    public void testSetDecoder() {
        final Decoder decoder = new Decoder();
        final List<CV> cvs = new ArrayList<>();
        decoder.setCvs(cvs);
        final CV cv = mock(CV.class);
        when(cv.getCvNumber()).thenReturn(1,2);
        when(cv.getCvValue()).thenReturn(5,3);
        cvs.add(cv);
        cvs.add(cv);
        cvHandler.setDecoder(decoder);
        assertEquals(2, cvHandler.getCVCache().keySet().size());
    }

    @Test
    public void testSetDecoderNullCvs() {
        final Decoder decoder = new Decoder();
        cvHandler.setDecoder(decoder);
        assertTrue(cvHandler.getCVCache().keySet().isEmpty());
    }

    @Test
    public void readCVTest() {
        when(messageResponse.getStatus()).thenReturn(MessageResponse.MessageStatus.OK);
        when(messageResponse.get(eq("CVData"))).thenReturn(123);
        assertEquals(new Integer(123), cvHandler.readCV(1));
        assertEquals(new Integer(123), cvHandler.readCV(1));
        assertEquals(1, cvHandler.getCVCache().size());
    }

    @Test
    public void testWriteCV() {
        cvHandler.writeCV(new CV());
        verify(dccInterface, times(1)).sendMessage(any(Message.class));
    }

    @Test
    public void readFailTest() {
        when(messageResponse.getStatus()).thenReturn(MessageResponse.MessageStatus.ERROR);
        when(messageResponse.get(eq("CVData"))).thenReturn(123);
        assertNull(cvHandler.readCV(1));
    }

}
