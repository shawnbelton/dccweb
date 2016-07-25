package uk.co.redkiteweb.dccweb.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ShutdownMessage;
import uk.co.redkiteweb.dccweb.demo.messages.DemoMessage;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 25/07/16.
 */
@RunWith(JUnit4.class)
public class DemoMessageProcessorTest {

    private DemoMessageProcessor demoMessageProcessor;
    private ApplicationContext context;

    @Before
    public void setUp() {
        context = mock(ApplicationContext.class);
        this.demoMessageProcessor = new DemoMessageProcessor();
        this.demoMessageProcessor.setApplicationContext(context);
    }

    @Test
    public void messageProcessorTest() {
        final DemoMessage demoMessage = mock(DemoMessage.class);
        final MessageResponse messageResponse = mock(MessageResponse.class);
        when(context.getBean(anyString(), eq(DemoMessage.class))).thenReturn(demoMessage);
        when(demoMessage.process(any(Message.class))).thenReturn(messageResponse);
        demoMessageProcessor.process(new ShutdownMessage());
        verify(demoMessage, times(1)).process(any(Message.class));
    }

}
