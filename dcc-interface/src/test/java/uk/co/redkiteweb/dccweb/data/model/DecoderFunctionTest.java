package uk.co.redkiteweb.dccweb.data.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 25/10/16.
 */
@RunWith(JUnit4.class)
public class DecoderFunctionTest {

    private DecoderFunction decoderFunction;

    @Before
    public void setup() {
        decoderFunction = new DecoderFunction();
        decoderFunction.setDecoderId(1);
        decoderFunction.setFunctionId(1);
        decoderFunction.setNumber(1);
        decoderFunction.setName("Sound");
    }

    @Test
    public void decoderIdTest() {
        assertEquals(new Integer(1), decoderFunction.getDecoderId());
    }

    @Test
    public void functionIdTest() {
        assertEquals(new Integer(1), decoderFunction.getFunctionId());
    }

    @Test
    public void numberTest() {
        assertEquals(new Integer(1), decoderFunction.getNumber());
    }

    @Test
    public void nameTest() {
        assertEquals("Sound", decoderFunction.getName());
    }
}
