package uk.co.redkiteweb.dccweb.dccinterface;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.store.LogStore;
import uk.co.redkiteweb.dccweb.dccinterface.factories.MessageProcessor;
import uk.co.redkiteweb.dccweb.dccinterface.factories.MessageProcessorFactory;
import uk.co.redkiteweb.dccweb.dccinterface.messages.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
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
    private LogStore logStore;

    @Before
    public void setUp() {
        dccInterfaceStatus = mock(DccInterfaceStatus.class);
        messageProcessorFactory = mock(MessageProcessorFactory.class);
        messageProcessor = mock(MessageProcessor.class);
        logStore = mock(LogStore.class);
        when(messageProcessor.getInterfaceCode()).thenReturn("Code");
        when(messageProcessor.getInterfaceName()).thenReturn("Name");
        dccInterface = new DccInterfaceImpl();
        dccInterface.setDccInterfaceStatus(dccInterfaceStatus);
        dccInterface.setMessageProcessorFactory(messageProcessorFactory);
        dccInterface.setLogStore(logStore);
        final List<MessageProcessor> messageProcessors = new ArrayList<MessageProcessor>();
        messageProcessors.add(messageProcessor);
        when(messageProcessorFactory.getAllInterfaces()).thenReturn(messageProcessors);
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

    @Test
    public void testSendMessage() {
        dccInterface.sendMessage(new EnterProgramMessage());
        dccInterface.sendMessage(new ExitProgramMessage());
        verify(messageProcessor, times(2)).process(any(Message.class));
    }

    @Test
    public void testAllInterfaces() {
        assertEquals(1, dccInterface.getInterfaces().size());
    }
}
