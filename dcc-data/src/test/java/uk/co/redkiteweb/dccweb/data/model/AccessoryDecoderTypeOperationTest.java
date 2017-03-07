package uk.co.redkiteweb.dccweb.data.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 16/02/17.
 */
@RunWith(JUnit4.class)
public class AccessoryDecoderTypeOperationTest {

    private AccessoryDecoderTypeOperation accessoryDecoderTypeOperation;

    @Before
    public void setup() {
        accessoryDecoderTypeOperation = new AccessoryDecoderTypeOperation();
        accessoryDecoderTypeOperation.setDecoderTypeOperationId(1);
        accessoryDecoderTypeOperation.setDecoderTypeId(2);
        accessoryDecoderTypeOperation.setDecoderTypeOperation("TEST");
        accessoryDecoderTypeOperation.setDecoderOperationValue(0);
    }

    @Test
    public void testOperationId() {
        assertEquals(new Integer(1),  accessoryDecoderTypeOperation.getDecoderTypeOperationId());
    }

    @Test
    public void testTypeId() {
        assertEquals(new Integer(2), accessoryDecoderTypeOperation.getDecoderTypeId());
    }

    @Test
    public void testOperation() {
        assertEquals("TEST", accessoryDecoderTypeOperation.getDecoderTypeOperation());
    }

    @Test
    public void testOperationValue() {
        assertEquals(new Integer(0), accessoryDecoderTypeOperation.getDecoderOperationValue());
    }
}
