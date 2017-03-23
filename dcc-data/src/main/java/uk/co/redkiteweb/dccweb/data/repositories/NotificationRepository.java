package uk.co.redkiteweb.dccweb.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.redkiteweb.dccweb.data.model.Notification;

import java.util.Date;
import java.util.List;

/**
 * Created by shawn on 21/03/17.
 */
@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {

    void deleteByCreatedBefore(final Date expiresOn);
    List<Notification> findByNotificationIdAfterOrderByNotificationIdAsc(final Long notificationId);

}
