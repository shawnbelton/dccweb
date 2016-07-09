package uk.co.redkiteweb.dccweb.dccinterface;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.dccinterface.factories.MessageProcessor;
import uk.co.redkiteweb.dccweb.dccinterface.factories.MessageProcessorFactory;
import uk.co.redkiteweb.dccweb.dccinterface.messages.KeepAliveMessage;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ShutdownMessage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 17/06/16.
 */
@RunWith(JUnit4.class)
public class DccInterfaceTest {

    private DccInterfaceImpl dccInterface;
    private DccInterfaceStatus dccInterfaceStatus;
    private MessageProcessorFactory messageProcessorFactory;
    private MessageProcessor messageProcessor;

    @Before
    public void setUp() {
        dccInterfaceStatus = mock(DccInterfaceStatus.class);
        messageProcessorFactory = mock(MessageProcessorFactory.class);
        messageProcessor = mock(MessageProcessor.class);
        dccInterface = new DccInterfaceImpl();
        dccInterface.setDccInterfaceStatus(dccInterfaceStatus);
        dccInterface.setMessageProcessorFactory(messageProcessorFactory);
        when(messageProcessorFactory.getInstance()).thenReturn(messageProcessor);
    }

    @Test
    public void testInitialise() {
        dccInterface.initialise();
        verify(dccInterfaceStatus, times(1)).setDisconnected();
    }

    @Test
    public void testStatus() {
        when(dccInterfaceStatus.getStatus()).thenReturn(DccInterfaceStatus.Status.DISCONNECTED);
        assertEquals(DccInterfaceStatus.Status.DISCONNECTED, dccInterface.getInterfaceStatus().getStatus());
    }

    @Test
    public void testShutdown() {
        dccInterface.shutdown();
        verify(messageProcessor, times(1)).process(any(ShutdownMessage.class));
    }

    @Test
    public void testCheckInterfaceOK() {
        final MessageResponse messageResponse = mock(MessageResponse.class);
        when(messageProcessor.process(any(KeepAliveMessage.class))).thenReturn(messageResponse);
        when(messageResponse.getStatus()).thenReturn(MessageResponse.MessageStatus.OK);
        dccInterface.checkInterface();
        verify(dccInterfaceStatus, times(1)).setConnected();
    }

    @Test
    public void testCheckInterfaceOffLine() {
        final MessageResponse messageResponse = mock(MessageResponse.class);
        when(messageProcessor.process(any(KeepAliveMessage.class))).thenReturn(messageResponse);
        when(messageResponse.getStatus()).thenReturn(MessageResponse.MessageStatus.ERROR);
        dccInterface.checkInterface();
        verify(dccInterfaceStatus, times(1)).setOffLine();
    }

    @Test
    public void testCheckInterfaceDisconnected() {
        final MessageResponse messageResponse = mock(MessageResponse.class);
        when(messageProcessor.process(any(KeepAliveMessage.class))).thenReturn(messageResponse);
        when(messageResponse.getStatus()).thenReturn(MessageResponse.MessageStatus.ERROR);
        when(messageResponse.get(eq("ERROR"))).thenReturn("Disconnected");
        dccInterface.checkInterface();
        verify(dccInterfaceStatus, times(1)).setDisconnected();
    }
}
