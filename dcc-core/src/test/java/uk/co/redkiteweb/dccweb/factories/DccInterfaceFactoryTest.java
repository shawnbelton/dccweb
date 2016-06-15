package uk.co.redkiteweb.dccweb.factories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import uk.co.redkiteweb.dccweb.dccInterface.DccInterface;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 15/06/16.
 */
@RunWith(JUnit4.class)
public class DccInterfaceFactoryTest {

    private DccInterfaceFactory dccInterfaceFactory;
    private ApplicationContext context;

    @Before
    public void setUp() {
        dccInterfaceFactory = new DccInterfaceFactory();
        context = mock(ApplicationContext.class);
        dccInterfaceFactory.setInterfaceId("Nce");
        dccInterfaceFactory.setApplicationContext(context);
    }

    @Test
    public void getInstance() {
        final DccInterface dccInterface = mock(DccInterface.class);
        when(context.getBean("Nce", DccInterface.class)).thenReturn(dccInterface);
        assertTrue(dccInterfaceFactory.getInstance() instanceof DccInterface);
    }

}
