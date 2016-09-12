package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Train;
import uk.co.redkiteweb.dccweb.webapp.data.Cab;
import uk.co.redkiteweb.dccweb.webapp.data.store.CabStore;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by shawn on 12/09/16.
 */
@RunWith(JUnit4.class)
public class CabInterfaceTest {

    private CabInterface cabInterface;
    private CabStore cabStore;

    @Before
    public void setup() {
        cabStore = mock(CabStore.class);
        cabInterface = new CabInterface();
        cabInterface.setCabStore(cabStore);
    }

    @Test
    public void testGetCab() {
        cabInterface.getCab(new Train());
        verify(cabStore, times(1)).getCab(any(Train.class));
    }

    @Test
    public void testUpdateCab() {
        cabInterface.updateCab(new Cab());
        verify(cabStore, times(1)).putCab(any(Cab.class));
    }

}
