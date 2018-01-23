package uk.co.redkiteweb.dccweb.demo.registers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 25/07/16.
 */
@RunWith(JUnit4.class)
public class DecoderRegisterTest {

    private DecoderRegister decoderRegister;

    @Before
    public void setUp() {
        decoderRegister = new DecoderRegister();
        decoderRegister.initialise();
    }

    @Test
    public void cvTest() {
        decoderRegister.setCV(1, 2);
        decoderRegister.setCV(2, 3);
        decoderRegister.setCV(3, 4);
        assertEquals(new Integer(2), decoderRegister.getCV(1));
    }



}
