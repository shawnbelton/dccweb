package uk.co.redkiteweb.dccweb.nce.factories;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by shawn on 19/06/16.
 */
@Component
public class NcePortIdentifier {

    private String connectionName;

    @Value("${nce.port}")
    public void setConnectionName(final String connectionName) {
        this.connectionName = connectionName;
    }

    public CommPortIdentifier getInstance() throws NoSuchPortException {
        System.setProperty("gnu.io.rxtx.SerialPorts", connectionName);
        return CommPortIdentifier.getPortIdentifier(connectionName);
    }

}
