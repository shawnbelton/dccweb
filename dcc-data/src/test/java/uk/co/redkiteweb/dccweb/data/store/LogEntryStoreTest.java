package uk.co.redkiteweb.dccweb.data.store;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import uk.co.redkiteweb.dccweb.data.service.NotificationService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 14/09/16.
 */
@RunWith(JUnit4.class)
public class LogEntryStoreTest {

    private LogStore logStore;
    private NotificationService notificationService;

    @Before
    public void setup() {
        notificationService = mock(NotificationService.class);
        logStore = new LogStore();
        logStore.setNotificationService(notificationService);
        logStore.setMessagingTemplate(mock(SimpMessagingTemplate.class));
    }

    @Test
    public void testLogging() {
        logStore.log("info", "message");
        assertEquals("info", logStore.getLastSix().get(0).getLevel());
        assertEquals("message", logStore.getLastSix().get(0).getMessage());
        verify(notificationService, times(1)).createNotification(anyString(), anyString());
    }

    @Test
    public void testReturnSix() {
        logStore.log("info", "message");
        logStore.log("info", "message");
        logStore.log("info", "message");
        logStore.log("info", "message");
        logStore.log("info", "message");
        logStore.log("info", "message");
        logStore.log("info", "message");
        assertEquals(6, logStore.getLastSix().size());
    }
}
