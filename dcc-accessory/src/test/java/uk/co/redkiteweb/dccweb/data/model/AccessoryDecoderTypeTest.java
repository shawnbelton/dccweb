package uk.co.redkiteweb.dccweb.data.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by shawn on 23/01/17.
 */
@RunWith(JUnit4.class)
public class AccessoryDecoderTypeTest {

    private AccessoryDecoderType accessoryDecoderType;

    @Before
    public void setup() {
        accessoryDecoderType = new AccessoryDecoderType();
        accessoryDecoderType.setDecoderTypeId(1);
        accessoryDecoderType.setDecoderType("Signal");
        accessoryDecoderType.setDecoderTypeCode("SIGNAL");
        accessoryDecoderType.setDecoderTypeOperations(new ArrayList<>());
    }

    @Test
    public void testId() {
        assertEquals(new Integer(1), accessoryDecoderType.getDecoderTypeId());
    }

    @Test
    public void testType() {
        assertEquals("Signal", accessoryDecoderType.getDecoderType());
    }

    @Test
    public void testCode() {
        assertEquals("SIGNAL", accessoryDecoderType.getDecoderTypeCode());
    }

    @Test
    public void testOperations() {
        assertNotNull(accessoryDecoderType.getDecoderTypeOperations());
    }
}
