package uk.co.redkiteweb.dccweb.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.Cab;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 17/01/17.
 */
@RunWith(JUnit4.class)
public class AsyncCabServiceTest {

    private AsyncCabService asyncCabService;
    private CabService cabService;

    @Before
    public void setup() {
        cabService = mock(CabService.class);
        asyncCabService = new AsyncCabService();
        asyncCabService.setCabService(cabService);
    }

    @Test
    public void updateCabFunctionsTest() {
        asyncCabService.updateCabFunctions(new Cab());
        verify(cabService, times(1)).updateCabFunctions(any(Cab.class));
    }

    @Test
    public void updateCabTest() {
        asyncCabService.updateCab(new Cab());
        verify(cabService, times(1)).updateCab(any(Cab.class));
    }
}
