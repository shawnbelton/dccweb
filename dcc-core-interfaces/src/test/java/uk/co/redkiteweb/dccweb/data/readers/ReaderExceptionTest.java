package uk.co.redkiteweb.dccweb.data.readers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by shawn on 28/10/16.
 */
@RunWith(JUnit4.class)
public class ReaderExceptionTest {

    @Test
    public void messageConstructorTest() {
        final Exception exception = new ReaderException("Error message");
        assertEquals("Error message", exception.getMessage());
    }

    @Test
    public void messageThrowableConstructorTest() {
        final Exception exception = new ReaderException("Error message", new Exception());
        assertNotNull(exception.getCause());
        assertEquals("Error message", exception.getMessage());
    }
}
