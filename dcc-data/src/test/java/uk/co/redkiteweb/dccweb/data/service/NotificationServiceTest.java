package uk.co.redkiteweb.dccweb.data.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Notification;
import uk.co.redkiteweb.dccweb.data.repositories.NotificationRepository;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 22/03/17.
 */
@RunWith(JUnit4.class)
public class NotificationServiceTest {

    private NotificationService notificationService;
    private NotificationRepository notificationRepository;

    @Before
    public void setup() {
        notificationRepository = mock(NotificationRepository.class);
        notificationService = new NotificationService();
        notificationService.setNotificationRepository(notificationRepository);
    }

    @Test
    public void testCreateNotification() {
        notificationService.createNotification("STATUS", "");
        verify(notificationRepository, times(1)).save(any(Notification.class));
    }
}
