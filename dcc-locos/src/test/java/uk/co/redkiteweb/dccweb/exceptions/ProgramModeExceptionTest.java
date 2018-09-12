package uk.co.redkiteweb.dccweb.exceptions;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProgramModeExceptionTest {

    @Test
    public void testMessage() {
        final ProgramModeException exception = new ProgramModeException("Message");
        assertEquals("Message", exception.getMessage());
    }

    @Test
    public void testThrowable() {
        final ProgramModeException exception = new ProgramModeException(new IOException("File Missing"));
        assertTrue(exception.getCause() instanceof IOException);
    }

    @Test
    public void testMessageAndThrowable() {
        final ProgramModeException exception = new ProgramModeException("Message", new IOException("File Missing"));
        assertEquals("Message", exception.getMessage());
        assertTrue(exception.getCause() instanceof IOException);
    }


}
