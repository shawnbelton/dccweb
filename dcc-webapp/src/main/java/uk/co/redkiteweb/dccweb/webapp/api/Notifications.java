package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.co.redkiteweb.dccweb.data.model.Notification;
import uk.co.redkiteweb.dccweb.data.repositories.NotificationRepository;

import java.util.List;

/**
 * Created by shawn on 23/03/17.
 */
@RestController
public class Notifications {

    private NotificationRepository notificationRepository;

    @Autowired
    public void setNotificationRepository(final NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @RequestMapping(value = "/notifications/{lastId}", method = RequestMethod.GET)
    public List<Notification> getNotifications(@PathVariable Long lastId) {
        return notificationRepository.findByNotificationIdAfterOrderByNotificationIdAsc(lastId);
    }
}
