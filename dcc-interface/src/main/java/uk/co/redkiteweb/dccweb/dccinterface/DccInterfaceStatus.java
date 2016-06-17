package uk.co.redkiteweb.dccweb.dccinterface;

import org.springframework.context.annotation.Scope;
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

    public DccInterfaceStatus() {
        status = Status.DISCONNECTED;
    }

    public Status getStatus() {
        return status;
    }

    public void setConnected() {
        status = Status.CONNECTED;
    }

    public void setDisconnected() {
        status = Status.DISCONNECTED;
    }

    public void setOffLine() {
        status = Status.OFF_LINE;
    }
}
