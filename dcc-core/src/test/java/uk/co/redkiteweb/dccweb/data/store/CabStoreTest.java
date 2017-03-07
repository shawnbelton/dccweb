package uk.co.redkiteweb.dccweb.data.store;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.Cab;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.model.DecoderFunction;
import uk.co.redkiteweb.dccweb.data.model.Train;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderFunctionRepository;
import uk.co.redkiteweb.dccweb.data.repositories.TrainRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 12/09/16.
 */
@RunWith(JUnit4.class)
public class CabStoreTest {

    private CabStore cabStore;
    private Train train;
    private DecoderFunctionRepository decoderFunctionRepository;

    @Before
    public void setup() {
        decoderFunctionRepository = mock(DecoderFunctionRepository.class);
        final TrainRepository trainRepository = mock(TrainRepository.class);
        cabStore = new CabStore();
        cabStore.setDecoderFunctionRepository(decoderFunctionRepository);
        cabStore.setTrainRepository(trainRepository);
        train = new Train();
        train.setTrainId(1);
        train.setNumber("12345");
        when(trainRepository.findOne(anyInt())).thenReturn(train);
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
        when(decoderFunctionRepository.findAllByDecoderId(anyInt())).thenReturn(new ArrayList<DecoderFunction>());
        train.setDecoder(decoder);
        assertEquals(0, cabStore.getCab(train).getCabFunctions().size());
    }

    @Test
    public void testDecoderOneFunction() {
        final Decoder decoder = new Decoder();
        final List<DecoderFunction> decoderFunctions = new ArrayList<DecoderFunction>();
        decoderFunctions.add(getDecoderFunction(1, "Name"));
        when(decoderFunctionRepository.findAllByDecoderId(anyInt())).thenReturn(decoderFunctions);
        train.setDecoder(decoder);
        assertEquals(1, cabStore.getCab(train).getCabFunctions().size());
    }

    @Test
    public void testDecoderFunctionAdded() {
        final Decoder decoder = new Decoder();
        final List<DecoderFunction> decoderFunctions = new ArrayList<DecoderFunction>();
        decoderFunctions.add(getDecoderFunction(1, "Name"));
        when(decoderFunctionRepository.findAllByDecoderId(anyInt())).thenReturn(decoderFunctions);
        train.setDecoder(decoder);
        assertEquals(1, cabStore.getCab(train).getCabFunctions().size());
        decoderFunctions.add(getDecoderFunction(2, "Name2"));
        assertEquals(2, cabStore.getCab(train).getCabFunctions().size());
    }

    @Test
    public void testGetCabByTrainId() {
        final Decoder decoder = new Decoder();
        train.setDecoder(decoder);
        assertEquals(0, cabStore.getCab(1).getCabFunctions().size());
    }

    private DecoderFunction getDecoderFunction(final int number, final String name) {
        final DecoderFunction decoderFunction = new DecoderFunction();
        decoderFunction.setNumber(number);
        decoderFunction.setName(name);
        return decoderFunction;
    }
}
