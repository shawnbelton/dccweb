package uk.co.redkiteweb.dccweb.nce.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by shawn on 17/06/16.
 */
@RunWith(JUnit4.class)
public class ConnectionExceptionTest {

    @Test
    public void messageTest() {
        final Exception exception = new ConnectionException("message");
        assertEquals("message", exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void messageCauseTest() {
        final Exception exception = new ConnectionException("message", new Exception());
        assertEquals("message", exception.getMessage());
        assertNotNull(exception.getCause());
    }
}
