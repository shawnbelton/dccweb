package uk.co.redkiteweb.dccweb.dccinterface.messages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 08/11/16.
 */
@RunWith(JUnit4.class)
public class UpdateFunctionsMessageTest {

    private UpdateFunctionsMessage updateFunctionsMessage;

    @Before
    public void setup() {
        updateFunctionsMessage = new UpdateFunctionsMessage();
        updateFunctionsMessage.setAddress(1);
        updateFunctionsMessage.setAddressMode(false);
        updateFunctionsMessage.addFunction(2, true);
    }

    @Test
    public void testAddress() {
        assertEquals(1, updateFunctionsMessage.getAddress());
    }

    @Test
    public void testAddressMode() {
        assertEquals(false, updateFunctionsMessage.isAddressMode());
    }

    @Test
    public void testFunctionExist() {
        assertEquals(true, updateFunctionsMessage.getFunctionState(2));
    }

    @Test
    public void testFunctionNotExist() {
        assertEquals(false, updateFunctionsMessage.getFunctionState(1));
    }
}
