package uk.co.redkiteweb.dccweb.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 06/03/17.
 */
@RunWith(JUnit4.class)
public class AccessoryOperationTest {

    private AccessoryOperation accessoryOperation;

    @Before
    public void setup() {
        accessoryOperation = new AccessoryOperation();
        accessoryOperation.setAccessoryAddress(1);
        accessoryOperation.setOperationValue(0);
    }

    @Test
    public void testAddress() {
        assertEquals(new Integer(1), accessoryOperation.getAccessoryAddress());
    }

    @Test
    public void testOperation() {
        assertEquals(new Integer(0), accessoryOperation.getOperationValue());
    }
}
