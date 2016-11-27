package uk.co.redkiteweb.dccweb.webapp.data;

import java.io.Serializable;

/**
 * Created by shawn on 26/11/16.
 */
public class Settings implements Serializable {

    private static final long serialVersionUID = 7001;

    private String dccSystem;
    private String serialPort;

    public String getDccSystem() {
        return dccSystem;
    }

    public void setDccSystem(String dccSystem) {
        this.dccSystem = dccSystem;
    }

    public String getSerialPort() {
        return serialPort;
    }

    public void setSerialPort(String serialPort) {
        this.serialPort = serialPort;
    }
}
