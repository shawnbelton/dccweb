package uk.co.redkiteweb.dccweb.services;

import com.google.common.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import uk.co.redkiteweb.dccweb.events.SendEvent;

import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class NotificationServiceTest {

    private NotificationService notificationService;
    private SimpMessagingTemplate messagingTemplate;

    @Before
    public void setup() {
        messagingTemplate = mock(SimpMessagingTemplate.class);
        notificationService = new NotificationService();
        notificationService.setMessagingTemplate(messagingTemplate);
    }

    @Test
    public void testRegister() {
        final EventBus eventBus = mock(EventBus.class);
        notificationService.setEventBus(eventBus);
        verify(eventBus, times(1)).register(eq(notificationService));
    }

    @Test
    public void testSendEvent() {
        final SendEvent sendEvent = mock(SendEvent.class);
        when(sendEvent.getUrl()).thenReturn("/send");
        when(sendEvent.sendObject()).thenReturn(Boolean.TRUE);
        notificationService.sendEventListener(sendEvent);
        verify(messagingTemplate, times(1)).convertAndSend(eq("/send"), any(Boolean.class));
    }
}
