package uk.co.redkiteweb.dccweb.data.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 14/09/16.
 */
@RunWith(JUnit4.class)
public class LogEntryTest {

    private LogEntry logEntry;

    @Before
    public void setup() {
        logEntry = new LogEntry();
        logEntry.setLevel("info");
        logEntry.setMessage("message");
    }

    @Test
    public void testLevel() {
        assertEquals("info", logEntry.getLevel());
    }

    @Test
    public void testMessage() {
        assertEquals("message", logEntry.getMessage());
    }
}
