package uk.co.redkiteweb.dccweb.webapp.listeners;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.event.ContextClosedEvent;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.factories.DccInterfaceFactory;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 18/06/16.
 */
@RunWith(JUnit4.class)
public class ShutdownListenerTest {

    private ShutdownListener shutdownListener;
    private DccInterfaceFactory dccInterfaceFactory;
    private DccInterface dccInterface;

    @Before
    public void setUp() {
        dccInterfaceFactory = mock(DccInterfaceFactory.class);
        dccInterface = mock(DccInterface.class);
        shutdownListener = new ShutdownListener();
        when(dccInterfaceFactory.getInstance()).thenReturn(dccInterface);
        shutdownListener.setDccInterfaceFactory(dccInterfaceFactory);
    }

    @Test
    public void shutdownTest() {
        shutdownListener.onApplicationEvent(mock(ContextClosedEvent.class));
        verify(dccInterface, times(1)).shutdown();
    }
}
