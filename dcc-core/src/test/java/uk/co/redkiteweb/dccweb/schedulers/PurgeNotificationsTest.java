package uk.co.redkiteweb.dccweb.schedulers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.repositories.NotificationRepository;

import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 21/03/17.
 */
@RunWith(JUnit4.class)
public class PurgeNotificationsTest {

    private PurgeNotifications purgeNotifications;
    private NotificationRepository notificationRepository;

    @Before
    public void setup() {
        notificationRepository = mock(NotificationRepository.class);
        purgeNotifications = new PurgeNotifications();
        purgeNotifications.setNotificationRepository(notificationRepository);
    }

    @Test
    public void testPurge() {
        purgeNotifications.purge();
        verify(notificationRepository, times(1)).deleteByCreatedBefore(any(Date.class));
    }
}
