package uk.co.redkiteweb.dccweb.nce.communication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 18/01/17.
 */
@RunWith(JUnit4.class)
public class TimerTest {

    @Test
    public void testTimedOut() {
        final Timer timer = new Timer(-1000);
        timer.start();
        assertEquals(true, timer.hasTimedOut());
    }

    @Test
    public void testNotTimedOut() {
        final Timer timer = new Timer(1000);
        timer.start();
        assertEquals(false, timer.hasTimedOut());
    }
}
