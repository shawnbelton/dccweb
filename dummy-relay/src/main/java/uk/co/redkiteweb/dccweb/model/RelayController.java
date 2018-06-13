package uk.co.redkiteweb.dccweb.model;

import java.io.Serializable;

/**
 * Created by shawn on 31/05/17.
 */
public class RelayController implements Serializable {

    private static final long serialVersionUID = 9011;

    private String controllerId;
    private String ipAddress;
    private String controllerName;
    private Integer value;

    public String getControllerId() {
        return controllerId;
    }

    public void setControllerId(final String controllerId) {
        this.controllerId = controllerId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(final String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(final String controllerName) {
        this.controllerName = controllerName;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(final Integer value) {
        this.value = value;
    }
}
