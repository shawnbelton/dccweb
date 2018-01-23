package uk.co.redkiteweb.dccweb.decoders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by shawn on 18/09/16.
 */
@RunWith(JUnit4.class)
public class DecoderNotDetectedExceptionTest {

    @Test
    public void testMessage() {
        final DecoderNotDetectedException definitionException = new DecoderNotDetectedException("Message");
        assertEquals("Message", definitionException.getMessage());
    }

    @Test
    public void testThrowable() {
        final DecoderNotDetectedException definitionException = new DecoderNotDetectedException(new IOException("File Missing"));
        assertTrue(definitionException.getCause() instanceof IOException);
    }

    @Test
    public void testMessageAndThrowable() {
        final DecoderNotDetectedException definitionException = new DecoderNotDetectedException("Message", new IOException("File Missing"));
        assertEquals("Message", definitionException.getMessage());
        assertTrue(definitionException.getCause() instanceof IOException);
    }
}
