package uk.co.redkiteweb.dccweb.dccinterface.factories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by shawn on 08/07/16.
 */
@RunWith(JUnit4.class)
public class MessageProcessingFactoryTest {

    private MessageProcessorFactory messageProcessorFactory;
    private ApplicationContext context;

    @Before
    public void setUp() {
        context = mock(ApplicationContext.class);
        messageProcessorFactory = new MessageProcessorFactory();
        messageProcessorFactory.setApplicationContext(context);
    }

    @Test
    public void getInstanceTest() {
        messageProcessorFactory.setInterfaceId("TEST");
        messageProcessorFactory.getInstance();
        verify(context, times(1)).getBean(eq("TEST"), eq(MessageProcessor.class));
    }
}
