package uk.co.redkiteweb.dccweb.webapp.data.store;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.model.DecoderFunction;
import uk.co.redkiteweb.dccweb.data.model.Train;
import uk.co.redkiteweb.dccweb.webapp.data.Cab;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 12/09/16.
 */
@RunWith(JUnit4.class)
public class CabStoreTest {

    private CabStore cabStore;
    private Train train;

    @Before
    public void setup() {
        cabStore = new CabStore();
        train = new Train();
        train.setTrainId(1);
        train.setNumber("12345");
    }

    @Test
    public void testCabPut() {
        final Cab cab = new Cab();
        cab.setTrain(train);
        cabStore.putCab(cab);
        assertEquals("12345", cabStore.getCab(train).getTrain().getNumber());
    }

    @Test
    public void testGetCab() {
        assertEquals("UP", cabStore.getCab(train).getDirection());
    }

    @Test
    public void testTrainWithDecoder() {
        final Decoder decoder = new Decoder();
        train.setDecoder(decoder);
        assertEquals(0, cabStore.getCab(train).getCabFunctions().size());
    }

    @Test
    public void testDecoderNoFunctions() {
        final Decoder decoder = new Decoder();
        decoder.setDecoderFunctions(new ArrayList<DecoderFunction>());
        train.setDecoder(decoder);
        assertEquals(0, cabStore.getCab(train).getCabFunctions().size());
    }

    @Test
    public void testDecoderOneFunction() {
        final Decoder decoder = new Decoder();
        decoder.setDecoderFunctions(new ArrayList<DecoderFunction>());
        decoder.getDecoderFunctions().add(getDecoderFunction(1, "Name"));
        train.setDecoder(decoder);
        assertEquals(1, cabStore.getCab(train).getCabFunctions().size());
    }

    @Test
    public void testDecoderFunctionAdded() {
        final Decoder decoder = new Decoder();
        decoder.setDecoderFunctions(new ArrayList<DecoderFunction>());
        decoder.getDecoderFunctions().add(getDecoderFunction(1, "Name"));
        train.setDecoder(decoder);
        assertEquals(1, cabStore.getCab(train).getCabFunctions().size());
        decoder.getDecoderFunctions().add(getDecoderFunction(2, "Name2"));
        assertEquals(2, cabStore.getCab(train).getCabFunctions().size());
    }

    private DecoderFunction getDecoderFunction(final int number, final String name) {
        final DecoderFunction decoderFunction = new DecoderFunction();
        decoderFunction.setNumber(number);
        decoderFunction.setName(name);
        return decoderFunction;
    }
}
