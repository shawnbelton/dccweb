package uk.co.redkiteweb.dccweb.data.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by shawn on 27/06/16.
 */
@RunWith(JUnit4.class)
public class LocoTest {

    private Loco loco;

    @Before
    public void setUp() {
        loco = new Loco();
        loco.setLocoId(1);
        loco.setName("test");
        loco.setNumber("12345");
        loco.setDecoder(new Decoder());
    }

    @Test
    public void testLoco() {
        assertEquals(new Integer(1), loco.getLocoId());
        assertEquals("test", loco.getName());
        assertEquals("12345", loco.getNumber());
        assertNotNull(loco.getDecoder());
    }

}
