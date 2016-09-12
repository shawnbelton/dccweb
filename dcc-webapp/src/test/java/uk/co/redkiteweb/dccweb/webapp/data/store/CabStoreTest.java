package uk.co.redkiteweb.dccweb.webapp.data.store;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Train;
import uk.co.redkiteweb.dccweb.webapp.data.Cab;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 12/09/16.
 */
@RunWith(JUnit4.class)
public class CabStoreTest {

    private CabStore cabStore;

    @Before
    public void setup() {
        cabStore = new CabStore();
    }

    @Test
    public void testCabPut() {
        final Train train = new Train();
        train.setTrainId(1);
        train.setNumber("12345");
        final Cab cab = new Cab();
        cab.setTrain(train);
        cabStore.putCab(cab);
        assertEquals("12345", cabStore.getCab(train).getTrain().getNumber());
    }

    @Test
    public void testGetCab() {
        final Train train = new Train();
        train.setTrainId(1);
        train.setNumber("12345");
        assertEquals("UP", cabStore.getCab(train).getDirection());
    }
}
