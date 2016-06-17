package uk.co.redkiteweb.dccweb.nce;

import gnu.io.SerialPort;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.AbstractDccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;
import uk.co.redkiteweb.dccweb.nce.factories.PortFactory;

/**
 * Created by shawn on 15/06/16.
 */
@Component("Nce")
public class NceInterface extends AbstractDccInterface {

    private static final Logger LOGGER = Logger.getLogger(NceInterface.class);

    private PortFactory portFactory;

    @Autowired
    public void setPortFactory(final PortFactory portFactory) {
        this.portFactory = portFactory;
    }

    @Override
    public void initialise() {
        super.initialise();
        LOGGER.info("Nce Interface Initialisation");
    }

    @Override
    public void checkInterface() {
        try {
            final SerialPort serialPort = portFactory.getSerialPort();
        } catch (ConnectionException excpetion) {
            this.getDccInterfaceStatus().setDisconnected();
            LOGGER.error(String.format("Connection error: %s", excpetion.getMessage()), excpetion);
        }
    }
}
