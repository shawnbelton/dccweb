package uk.co.redkiteweb.dccweb.data.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

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
        decoder.setDecoderId(1);
        decoder.setDccManufacturer(dccManufacturer);
    }

    @Test
    public void idTest() {
        assertEquals(new Integer(1), decoder.getDecoderId());
    }

    @Test
    public void manufacturerTest() {
        assertNotNull(decoder.getDccManufacturer());
    }
}
