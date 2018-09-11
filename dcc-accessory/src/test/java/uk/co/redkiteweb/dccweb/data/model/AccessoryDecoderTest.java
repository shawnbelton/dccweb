package uk.co.redkiteweb.dccweb.data.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by shawn on 23/01/17.
 */
@RunWith(JUnit4.class)
public class AccessoryDecoderTest {

    private AccessoryDecoder accessoryDecoder;

    @Before
    public void setup() {
        accessoryDecoder = new AccessoryDecoder();
        accessoryDecoder.setAccessoryDecoderId(1);
        accessoryDecoder.setAccessoryDecoderType(new AccessoryDecoderType());
        accessoryDecoder.setAddress(123);
        accessoryDecoder.setName("Label");
        accessoryDecoder.setCurrentValue(0);
        accessoryDecoder.setMacroId(1);
    }

    @Test
    public void testId() {
        assertEquals(new Integer(1), accessoryDecoder.getAccessoryDecoderId());
    }

    @Test
    public void testType() {
        assertNotNull(accessoryDecoder.getAccessoryDecoderType());
    }

    @Test
    public void testAddress() {
        assertEquals(new Integer(123), accessoryDecoder.getAddress());
    }

    @Test
    public void testName() {
        assertEquals("Label", accessoryDecoder.getName());
    }

    @Test
    public void testCurrentValue() {
        assertEquals(new Integer(0), accessoryDecoder.getCurrentValue());
    }

    @Test
    public void testMacro() {
        assertNotNull(accessoryDecoder.getMacroId());
    }
}
