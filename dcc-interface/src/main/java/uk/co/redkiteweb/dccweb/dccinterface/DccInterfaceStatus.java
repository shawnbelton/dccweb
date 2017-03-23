package uk.co.redkiteweb.dccweb.dccinterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.service.NotificationService;

/**
 * Created by shawn on 17/06/16.
 */
@Component
@Scope("prototype")
public class DccInterfaceStatus {

    public enum Status {
        DISCONNECTED,
        CONNECTED,
        OFF_LINE
    }

    private Status status;
    private NotificationService notificationService;

    public DccInterfaceStatus() {
        status = Status.DISCONNECTED;
    }

    @Autowired
    public void setNotificationService(final NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public Status getStatus() {
        return status;
    }

    public void setConnected() {
        setStatus(Status.CONNECTED);
    }

    public void setDisconnected() {
        setStatus(Status.DISCONNECTED);
    }

    public void setOffLine() {
        setStatus(Status.OFF_LINE);
    }

    private void setStatus(final Status newStatus) {
        if (status != newStatus) {
            notificationService.createNotification("STATUS", "");
        }
        status = newStatus;
    }
}
