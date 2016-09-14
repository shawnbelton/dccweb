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
public class LogTest {

    private Log log;

    @Before
    public void setup() {
        log = new Log();
        log.setLevel("info");
        log.setMessage("message");
    }

    @Test
    public void testLevel() {
        assertEquals("info", log.getLevel());
    }

    @Test
    public void testMessage() {
        assertEquals("message", log.getMessage());
    }
}
