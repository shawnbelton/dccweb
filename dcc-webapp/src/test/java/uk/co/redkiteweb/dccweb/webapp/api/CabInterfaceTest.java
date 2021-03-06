package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.Cab;
import uk.co.redkiteweb.dccweb.data.model.Loco;
import uk.co.redkiteweb.dccweb.data.store.CabStore;
import uk.co.redkiteweb.dccweb.services.AsyncCabService;

import static org.mockito.Mockito.*;

/**
 * Created by shawn on 12/09/16.
 */
@RunWith(JUnit4.class)
public class CabInterfaceTest {

    private CabInterface cabInterface;
    private CabStore cabStore;
    private AsyncCabService cabService;

    @Before
    public void setup() {
        cabService = mock(AsyncCabService.class);
        cabStore = mock(CabStore.class);
        cabInterface = new CabInterface();
        cabInterface.setCabService(cabService);
        cabInterface.setCabStore(cabStore);
    }

    @Test
    public void testGetCab() {
        cabInterface.getCab(new Loco());
        verify(cabStore, times(1)).getCab(any(Loco.class));
    }

    @Test
    public void testUpdateCab() {
        final Cab cab = new Cab();
        final Loco loco = new Loco();
        loco.setNumber("12345");
        loco.setLocoId(1);
        cab.setLoco(loco);
        cab.setSpeed(100);
        cab.setDirection("UP");
        cabInterface.updateCab(cab);
        verify(cabStore, times(1)).putCab(any(Cab.class));
        verify(cabService, times(1)).updateCab(any(Cab.class));
    }

    @Test
    public void testUpdateFunction() {
        final Cab cab = new Cab();
        final Loco loco = new Loco();
        loco.setNumber("12345");
        loco.setLocoId(1);
        cab.setLoco(loco);
        cabInterface.updateCabFunction(cab);
        verify(cabService, times(1)).updateCabFunctions(any(Cab.class));
        verify(cabStore, times(1)).putCab(any(Cab.class));
    }
}
