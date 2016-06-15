package uk.co.redkiteweb.dccweb.webapp.listeners;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.event.ContextRefreshedEvent;
import uk.co.redkiteweb.dccweb.dccInterface.DccInterface;
import uk.co.redkiteweb.dccweb.factories.DccInterfaceFactory;
import uk.co.redkiteweb.dccweb.webapp.listeners.StartUpListener;

import static org.mockito.Mockito.*;

/**
 * Created by shawn on 15/06/16.
 */
@RunWith(JUnit4.class)
public class StartUpListenerTest {

    private StartUpListener startUpListener;
    private DccInterfaceFactory dccInterfaceFactory;

    @Before
    public void setUp() {
        startUpListener = new StartUpListener();
        dccInterfaceFactory = mock(DccInterfaceFactory.class);
        startUpListener.setDccInterfaceFactory(dccInterfaceFactory);
    }

    @Test
    public void contextRefreshed() {
        final DccInterface dccInterface = mock(DccInterface.class);
        when(dccInterfaceFactory.getInstance()).thenReturn(dccInterface);
        final ContextRefreshedEvent contextRefreshedEvent = mock(ContextRefreshedEvent.class);
        startUpListener.onApplicationEvent(contextRefreshedEvent);
        verify(dccInterface, times(1)).initialise();
    }
}
