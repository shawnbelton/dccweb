package uk.co.redkiteweb.dccweb.schedulers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.repositories.NotificationRepository;

import java.util.Calendar;

/**
 * Created by shawn on 21/03/17.
 */
@Component
public class PurgeNotifications {

    private static final Logger LOGGER = LogManager.getLogger(PurgeNotifications.class);

    private NotificationRepository notificationRepository;

    @Autowired
    public void setNotificationRepository(final NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Scheduled(fixedDelay = 60000)
    public void purge() {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -10);
        notificationRepository.deleteByCreatedBefore(calendar.getTime());
        LOGGER.info("Deleted old notifications");
    }
}
