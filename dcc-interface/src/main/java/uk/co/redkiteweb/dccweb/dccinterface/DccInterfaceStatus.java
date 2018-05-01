package uk.co.redkiteweb.dccweb.dccinterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

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
    private SimpMessagingTemplate messagingTemplate;

    public DccInterfaceStatus() {
        status = Status.DISCONNECTED;
    }

    @Autowired
    public void setMessagingTemplate(final SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
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
            messagingTemplate.convertAndSend("/status", newStatus);
        }
        status = newStatus;
    }
}
