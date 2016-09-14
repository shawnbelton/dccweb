package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.data.Log;
import uk.co.redkiteweb.dccweb.data.store.LogStore;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 14/09/16.
 */
@RunWith(JUnit4.class)
public class LogMessagesTest {

    private LogMessages logMessages;
    private LogStore logStore;

    @Before
    public void setup() {
        logStore = mock(LogStore.class);
        logMessages = new LogMessages();
        logMessages.setLogStore(logStore);
    }

    @Test
    public void testGetMessages() {
        when(logStore.getLastSix()).thenReturn(new ArrayList<Log>());
        assertNotNull(logMessages.getMessages());
    }
}
