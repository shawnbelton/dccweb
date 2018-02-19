package uk.co.redkiteweb.dccweb.webapp.listeners;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import uk.co.redkiteweb.dccweb.data.loaders.Loader;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;

import java.util.HashMap;
import java.util.Map;

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
        dccInterface = mock(DccInterface.class);
        loader = mock(Loader.class);
        final ApplicationContext context = mock(ApplicationContext.class);
        startUpListener = new StartUpListener();
        startUpListener.setDccInterface(dccInterface);
        startUpListener.setApplicationContext(context);
        final Map<String, Loader> loaders = new HashMap<String, Loader>();
        loaders.put("testLoader", loader);
        when(context.getBeansOfType(eq(Loader.class))).thenReturn(loaders);
    }

    @Test
    public void contextRefreshed() {
        startUpListener.onApplicationEvent();
        verify(dccInterface, times(1)).initialise();
        verify(loader, times(1)).load();
    }
}
