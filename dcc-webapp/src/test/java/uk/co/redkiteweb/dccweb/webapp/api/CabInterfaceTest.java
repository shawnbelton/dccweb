package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Train;
import uk.co.redkiteweb.dccweb.webapp.data.Cab;
import uk.co.redkiteweb.dccweb.webapp.data.store.CabStore;
import uk.co.redkiteweb.dccweb.webapp.services.CabService;

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
    private CabService cabService;

    @Before
    public void setup() {
        cabService = mock(CabService.class);
        cabStore = mock(CabStore.class);
        cabInterface = new CabInterface();
        cabInterface.setCabService(cabService);
        cabInterface.setCabStore(cabStore);
    }

    @Test
    public void testGetCab() {
        cabInterface.getCab(new Train());
        verify(cabStore, times(1)).getCab(any(Train.class));
    }

    @Test
    public void testUpdateCab() {
        final Cab cab = new Cab();
        final Train train = new Train();
        train.setNumber("12345");
        train.setTrainId(1);
        cab.setTrain(train);
        cab.setSpeed(100);
        cab.setDirection("UP");
        cabInterface.updateCab(cab);
        verify(cabStore, times(1)).putCab(any(Cab.class));
        verify(cabService, times(1)).updateCab(any(Cab.class));
    }

}
