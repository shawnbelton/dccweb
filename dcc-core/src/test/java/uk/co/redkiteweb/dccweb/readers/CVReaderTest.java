package uk.co.redkiteweb.dccweb.readers;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 18/09/16.
 */
@RunWith(JUnit4.class)
public class CVReaderTest {

    private CVReader cvReader;
    private MessageResponse messageResponse;

    @Before
    public void setup() {
        final DccInterface dccInterface = mock(DccInterface.class);
        messageResponse = mock(MessageResponse.class);
        cvReader = new CVReader();
        cvReader.setDccInterface(dccInterface);
        when(dccInterface.sendMessage(any(Message.class))).thenReturn(messageResponse);
    }

    @Test
    public void testSetDecoder() {
        final Decoder decoder = mock(Decoder.class);
        final List<CV> cvs = new ArrayList<>();
        final CV cv = mock(CV.class);
        when(cv.getCvNumber()).thenReturn(1,2);
        when(cv.getCvValue()).thenReturn(5,3);
        cvs.add(cv);
        cvs.add(cv);
        when(decoder.getCvs()).thenReturn(cvs);
        cvReader.setDecoder(decoder);
        assertEquals(2, cvReader.getCVCache().keySet().size());
    }

    @Test
    public void readCVTest() {
        when(messageResponse.getStatus()).thenReturn(MessageResponse.MessageStatus.OK);
        when(messageResponse.get(eq("CVData"))).thenReturn(123);
        assertEquals(new Integer(123), cvReader.readCV(1));
        assertEquals(new Integer(123), cvReader.readCV(1));
        assertEquals(new Integer(123), cvReader.readCV(1,false));
        assertEquals(1, cvReader.getCVCache().size());
    }

    @Test
    public void readFailTest() {
        when(messageResponse.getStatus()).thenReturn(MessageResponse.MessageStatus.ERROR);
        when(messageResponse.get(eq("CVData"))).thenReturn(123);
        assertNull(cvReader.readCV(1));
    }

}
