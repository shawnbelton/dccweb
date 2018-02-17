package uk.co.redkiteweb.dccweb.dccinterface.messages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.CV;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class WriteCVMessageTest {

    private WriteCVMessage message;

    @Before
    public void setup() {
        message = new WriteCVMessage();
        final CV cv = new CV();
        cv.setCvValue(124);
        cv.setCvNumber(12);
        message.setCv(cv);
    }

    @Test
    public void testLogMessage() {
        assertEquals("Writing CV 12 with value 124", message.getLogMessage());
    }

    @Test
    public void testGetCV() {
        assertNotNull(message.getCv());
    }
}
