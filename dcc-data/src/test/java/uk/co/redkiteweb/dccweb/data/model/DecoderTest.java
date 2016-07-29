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
    private DccManufacturer dccManufacturer;

    @Before
    public void setUp() {
        decoder = new Decoder();
        dccManufacturer = new DccManufacturer();
        decoder.setDecoderId("L1S1");
        decoder.setDccManufacturer(dccManufacturer);
        decoder.setVersion(123);
        decoder.setShortAddress(101);
        decoder.setLongAddress(1024);
    }

    @Test
    public void idTest() {
        assertEquals("L1S1", decoder.getDecoderId());
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
    public void cvTest() {
        decoder.setCvs(new ArrayList<CV>());
        assertNotNull(decoder.getCvs());
    }
}
