package uk.co.redkiteweb.dccweb.async;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.store.LogStore;

import static org.mockito.Mockito.*;

/**
 * Created by shawn on 23/12/16.
 */
@RunWith(JUnit4.class)
public class AsyncUncaughtHandlerTest {

    private AsyncUncaughtHandler asyncUncaughtHandler;
    private LogStore logStore;

    @Before
    public void setup() {
        logStore = mock(LogStore.class);
        asyncUncaughtHandler = new AsyncUncaughtHandler();
        asyncUncaughtHandler.setLogStore(logStore);
    }

    @Test
    public void handleTest() {
        final Throwable throwable = mock(Throwable.class);
        when(throwable.getMessage()).thenReturn("Error");
        asyncUncaughtHandler.handleUncaughtException(throwable, null, null);
        verify(logStore, times(1)).log(eq("ERROR"), anyString());
    }
}
