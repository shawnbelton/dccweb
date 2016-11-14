package uk.co.redkiteweb.dccweb.webapp.listeners;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.event.ContextClosedEvent;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;

import static org.mockito.Mockito.*;

/**
 * Created by shawn on 18/06/16.
 */
@RunWith(JUnit4.class)
public class ShutdownListenerTest {

    private ShutdownListener shutdownListener;
    private DccInterface dccInterface;

    @Before
    public void setUp() {
        dccInterface = mock(DccInterface.class);
        shutdownListener = new ShutdownListener();
        shutdownListener.setDccInterface(dccInterface);
    }

    @Test
    public void shutdownTest() {
        shutdownListener.onApplicationEvent(mock(ContextClosedEvent.class));
        verify(dccInterface, times(1)).shutdown();
    }
}
