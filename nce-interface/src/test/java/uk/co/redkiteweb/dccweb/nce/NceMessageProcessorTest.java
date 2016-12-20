package uk.co.redkiteweb.dccweb.nce;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;
import uk.co.redkiteweb.dccweb.nce.messages.NceMessage;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 15/06/16.
 */
@RunWith(JUnit4.class)
public class NceMessageProcessorTest {

    private NceMessageProcessor nceMessageProcessor;
    private ApplicationContext applicationContext;

    @Before
    public void setUp() {
        applicationContext = mock(ApplicationContext.class);
        nceMessageProcessor = new NceMessageProcessor();
        nceMessageProcessor.setApplicationContext(applicationContext);
    }

    @Test
    public void testProcessMessage() throws ConnectionException {
        final Message message = mock(Message.class);
        final NceMessage nceMessage = mock(NceMessage.class);
        final MessageResponse messageResponse = mock(MessageResponse.class);
        when(applicationContext.getBean(anyString(), eq(NceMessage.class))).thenReturn(nceMessage);
        when(nceMessage.process(any(Message.class))).thenReturn(messageResponse);
        when(messageResponse.getStatus()).thenReturn(MessageResponse.MessageStatus.OK);
        assertNotNull(nceMessageProcessor.process(message));
    }

    @Test
    public void testConnectionException() throws ConnectionException {
        final Message message = mock(Message.class);
        final NceMessage nceMessage = mock(NceMessage.class);
        final MessageResponse messageResponse = mock(MessageResponse.class);
        when(applicationContext.getBean(anyString(), eq(NceMessage.class))).thenReturn(nceMessage);
        when(nceMessage.process(any(Message.class))).thenThrow(new ConnectionException("Error"));
        when(messageResponse.getStatus()).thenReturn(MessageResponse.MessageStatus.OK);
        assertNotNull(nceMessageProcessor.process(message));
    }

    @Test
    public void testCode() {
        assertEquals("Nce", nceMessageProcessor.getInterfaceCode());
    }

    @Test
    public void testName() {
        assertEquals("NCE DCC System", nceMessageProcessor.getInterfaceName());
    }
}
