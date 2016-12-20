package uk.co.redkiteweb.dccweb.dccinterface.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.dccinterface.factories.MessageProcessor;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 20/12/16.
 */
@RunWith(JUnit4.class)
public class InterfaceInfoTest {

    private InterfaceInfo interfaceInfo;

    @Before
    public void setup() {
        final MessageProcessor messageProcessor = mock(MessageProcessor.class);
        when(messageProcessor.getInterfaceCode()).thenReturn("Code");
        when(messageProcessor.getInterfaceName()).thenReturn("Name");
        interfaceInfo = InterfaceInfo.getInstance(messageProcessor);
    }

    @Test
    public void testCode() {
        assertEquals("Code", interfaceInfo.getCode());
    }

    @Test
    public void testName() {
        assertEquals("Name", interfaceInfo.getName());
    }
}
