package uk.co.redkiteweb.dccweb.nce.factories;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by shawn on 19/06/16.
 */
@Component
public class NcePortIdentifier {

    private Environment environment;

    @Autowired
    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }

    public CommPortIdentifier getInstance() throws NoSuchPortException {
        final String connectionName = environment.getProperty("nce.port");
        System.setProperty("gnu.io.rxtx.SerialPorts", connectionName);
        return CommPortIdentifier.getPortIdentifier(connectionName);
    }

}
