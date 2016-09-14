package uk.co.redkiteweb.dccweb.data.store;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 14/09/16.
 */
@RunWith(JUnit4.class)
public class LogStoreTest {

    private LogStore logStore;

    @Before
    public void setup() {
        logStore = new LogStore();
    }

    @Test
    public void testLogging() {
        logStore.log("info", "message");
        assertEquals("info", logStore.getLastSix().get(0).getLevel());
        assertEquals("message", logStore.getLastSix().get(0).getMessage());
    }

    @Test
    public void testReturnSix() {
        logStore.log("info", "message");
        logStore.log("info", "message");
        logStore.log("info", "message");
        logStore.log("info", "message");
        logStore.log("info", "message");
        logStore.log("info", "message");
        logStore.log("info", "message");
        assertEquals(6, logStore.getLastSix().size());
    }
}
