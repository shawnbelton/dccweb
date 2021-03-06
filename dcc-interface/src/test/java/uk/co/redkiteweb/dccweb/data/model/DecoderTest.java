package uk.co.redkiteweb.dccweb.data.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 07/07/16.
 */
@RunWith(JUnit4.class)
public class DecoderTest {

    private Decoder decoder;

    @Before
    public void setUp() {
        decoder = new Decoder();
        final DccManufacturer dccManufacturer = new DccManufacturer();
        decoder.setDecoderId(1234);
        decoder.setDccManufacturer(dccManufacturer);
        decoder.setVersion(123);
        decoder.setShortAddress(101);
        decoder.setLongAddress(1024);
        decoder.setCurrentAddress(1024);
        decoder.setAddressMode(true);
    }

    @Test
    public void idTest() {
        assertEquals(new Integer(1234), decoder.getDecoderId());
    }

    @Test
    public void manufacturerTest() {
        assertNotNull(decoder.getDccManufacturer());
    }

    @Test
    public void revisionTest() {
        assertEquals(new Integer(123), decoder.getVersion());
    }

    @Test
    public void shortAddressTest() {
        assertEquals(new Integer(101), decoder.getShortAddress());
    }

    @Test
    public void longAddressTest() {
        assertEquals(new Integer(1024), decoder.getLongAddress());
    }

    @Test
    public void currentAddressTest() {
        assertEquals(new Integer(1024), decoder.getCurrentAddress());
    }

    @Test
    public void cvTest() {
        decoder.setCvs(new ArrayList<>());
        assertNotNull(decoder.getCvs());
    }

    @Test
    public void decoderFunctionTest() {
        decoder.setDecoderFunctions(new ArrayList<>());
        assertNotNull(decoder.getDecoderFunctions());
    }

    @Test
    public void linkedMacrosTest() {
        decoder.setLinkedMacros(new ArrayList<>());
        assertNotNull(decoder.getLinkedMacros());
    }

    @Test
    public void decoderAddressModeTest() {
        assertEquals(true, decoder.getAddressMode());
    }
}
