package uk.co.redkiteweb.dccweb.schedulers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.factories.DccInterfaceFactory;

import static org.mockito.Mockito.*;

/**
 * Created by shawn on 17/06/16.
 */
@RunWith(JUnit4.class)
public class CheckInterfaceTest {

    private CheckInterface checkInterface;
    private DccInterfaceFactory dccInterfaceFactory;
    private DccInterface dccInterface;

    @Before
    public void setUp() {
        dccInterfaceFactory = mock(DccInterfaceFactory.class);
        dccInterface = mock(DccInterface.class);
        checkInterface = new CheckInterface();
        checkInterface.setDccInterfaceFactory(dccInterfaceFactory);
        when(dccInterfaceFactory.getInstance()).thenReturn(dccInterface);
    }

    @Test
    public void checkInterfaceTest() {
        checkInterface.checkInterface();
        verify(dccInterface, times(1)).checkInterface();
    }

}
