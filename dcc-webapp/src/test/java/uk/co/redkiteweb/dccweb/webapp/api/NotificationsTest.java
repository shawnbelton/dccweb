package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Notification;
import uk.co.redkiteweb.dccweb.data.repositories.NotificationRepository;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 23/03/17.
 */
@RunWith(JUnit4.class)
public class NotificationsTest {

    private Notifications notifications;
    private NotificationRepository notificationRepository;

    @Before
    public void setup() {
        notificationRepository = mock(NotificationRepository.class);
        notifications = new Notifications();
        notifications.setNotificationRepository(notificationRepository);
    }

    @Test
    public void testGetNotifications() {
        when(notificationRepository.findByNotificationIdAfterOrderByNotificationIdAsc(anyLong())).thenReturn(new ArrayList<Notification>());
        assertNotNull(notifications.getNotifications(-1L));
    }
}
