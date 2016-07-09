package uk.co.redkiteweb.dccweb.webapp.listeners;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.event.ContextRefreshedEvent;
import uk.co.redkiteweb.dccweb.data.loaders.Loader;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;

import static org.mockito.Mockito.*;

/**
 * Created by shawn on 15/06/16.
 */
@RunWith(JUnit4.class)
public class StartUpListenerTest {

    private StartUpListener startUpListener;
    private DccInterface dccInterface;
    private Loader loader;

    @Before
    public void setUp() {
        startUpListener = new StartUpListener();
        dccInterface = mock(DccInterface.class);
        loader = mock(Loader.class);
        startUpListener.setDccInterface(dccInterface);
        startUpListener.setDccManufacturerLoader(loader);
    }

    @Test
    public void contextRefreshed() {
        final ContextRefreshedEvent contextRefreshedEvent = mock(ContextRefreshedEvent.class);
        startUpListener.onApplicationEvent(contextRefreshedEvent);
        verify(dccInterface, times(1)).initialise();
        verify(loader, times(1)).load();
    }
}
