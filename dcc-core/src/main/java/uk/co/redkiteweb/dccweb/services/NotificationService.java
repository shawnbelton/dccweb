package uk.co.redkiteweb.dccweb.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.Notification;
import uk.co.redkiteweb.dccweb.data.repositories.NotificationRepository;

import java.util.Date;

/**
 * Created by shawn on 22/03/17.
 */
@Component
public class NotificationService {

    private static final Logger LOGGER = LogManager.getLogger(NotificationService.class);

    private NotificationRepository notificationRepository;

    @Autowired
    public void setNotificationRepository(final NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void createNotification(final String type, final String value) {
        LOGGER.info(String.format("Saving new notification %s %s", type, value));
        final Notification notification = new Notification();
        notification.setType(type);
        notification.setValue(value);
        notification.setCreated(new Date());
        notificationRepository.save(notification);
    }
}
