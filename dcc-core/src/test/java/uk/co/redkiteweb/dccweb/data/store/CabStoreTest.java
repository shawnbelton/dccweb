package uk.co.redkiteweb.dccweb.data.store;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.Cab;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.model.DecoderFunction;
import uk.co.redkiteweb.dccweb.data.model.Loco;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderFunctionRepository;
import uk.co.redkiteweb.dccweb.data.repositories.LocoRepository;

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
    private Loco loco;
    private DecoderFunctionRepository decoderFunctionRepository;

    @Before
    public void setup() {
        decoderFunctionRepository = mock(DecoderFunctionRepository.class);
        final LocoRepository locoRepository = mock(LocoRepository.class);
        cabStore = new CabStore();
        cabStore.setDecoderFunctionRepository(decoderFunctionRepository);
        cabStore.setLocoRepository(locoRepository);
        loco = new Loco();
        loco.setLocoId(1);
        loco.setNumber("12345");
        when(locoRepository.findOne(anyInt())).thenReturn(loco);
    }

    @Test
    public void testCabPut() {
        final Cab cab = new Cab();
        cab.setLoco(loco);
        cabStore.putCab(cab);
        assertEquals("12345", cabStore.getCab(loco).getLoco().getNumber());
    }

    @Test
    public void testGetCab() {
        assertEquals("UP", cabStore.getCab(loco).getDirection());
    }

    @Test
    public void testLocoWithDecoder() {
        final Decoder decoder = new Decoder();
        loco.setDecoder(decoder);
        assertEquals(0, cabStore.getCab(loco).getCabFunctions().size());
    }

    @Test
    public void testDecoderNoFunctions() {
        final Decoder decoder = new Decoder();
        when(decoderFunctionRepository.findAllByDecoderId(anyInt())).thenReturn(new ArrayList<DecoderFunction>());
        loco.setDecoder(decoder);
        assertEquals(0, cabStore.getCab(loco).getCabFunctions().size());
    }

    @Test
    public void testDecoderOneFunction() {
        final Decoder decoder = new Decoder();
        final List<DecoderFunction> decoderFunctions = new ArrayList<DecoderFunction>();
        decoderFunctions.add(getDecoderFunction(1, "Name"));
        when(decoderFunctionRepository.findAllByDecoderId(anyInt())).thenReturn(decoderFunctions);
        loco.setDecoder(decoder);
        assertEquals(1, cabStore.getCab(loco).getCabFunctions().size());
    }

    @Test
    public void testDecoderFunctionAdded() {
        final Decoder decoder = new Decoder();
        final List<DecoderFunction> decoderFunctions = new ArrayList<DecoderFunction>();
        decoderFunctions.add(getDecoderFunction(1, "Name"));
        when(decoderFunctionRepository.findAllByDecoderId(anyInt())).thenReturn(decoderFunctions);
        loco.setDecoder(decoder);
        assertEquals(1, cabStore.getCab(loco).getCabFunctions().size());
        decoderFunctions.add(getDecoderFunction(2, "Name2"));
        assertEquals(2, cabStore.getCab(loco).getCabFunctions().size());
    }

    @Test
    public void testGetCabByLocoId() {
        final Decoder decoder = new Decoder();
        loco.setDecoder(decoder);
        assertEquals(0, cabStore.getCab(1).getCabFunctions().size());
    }

    private DecoderFunction getDecoderFunction(final int number, final String name) {
        final DecoderFunction decoderFunction = new DecoderFunction();
        decoderFunction.setNumber(number);
        decoderFunction.setName(name);
        return decoderFunction;
    }
}
