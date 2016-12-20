package uk.co.redkiteweb.dccweb.dccinterface.factories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import uk.co.redkiteweb.dccweb.data.service.SettingsService;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 08/07/16.
 */
@RunWith(JUnit4.class)
public class MessageProcessingFactoryTest {

    private MessageProcessorFactory messageProcessorFactory;
    private ApplicationContext context;
    private SettingsService settingsService;

    @Before
    public void setUp() {
        settingsService = mock(SettingsService.class);
        context = mock(ApplicationContext.class);
        messageProcessorFactory = new MessageProcessorFactory();
        messageProcessorFactory.setApplicationContext(context);
        messageProcessorFactory.setSettingsService(settingsService);
        when(settingsService.getSettingValue(anyString(), anyString())).thenReturn("Demo");
    }

    @Test
    public void getInstanceTest() {
        messageProcessorFactory.getInstance();
        verify(context, times(1)).getBean(eq("Demo"), eq(MessageProcessor.class));
    }

    @Test
    public void testAllInterfaces() {
        when(context.getBeansOfType(eq(MessageProcessor.class))).thenReturn(new HashMap<String, MessageProcessor>());
        assertTrue(messageProcessorFactory.getAllInterfaces().isEmpty());
    }
}
