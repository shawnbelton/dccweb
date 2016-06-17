package uk.co.redkiteweb.dccweb.nce;

import gnu.io.SerialPort;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.AbstractDccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.nce.communication.NceData;
import uk.co.redkiteweb.dccweb.nce.communication.TalkToNCE;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;
import uk.co.redkiteweb.dccweb.nce.factories.PortFactory;

/**
 * Created by shawn on 15/06/16.
 */
@Component("Nce")
public class NceInterface extends AbstractDccInterface {

    private static final Logger LOGGER = Logger.getLogger(NceInterface.class);

    private TalkToNCE talkToNCE;

    @Autowired
    public void setTalkToNCE(final TalkToNCE talkToNCE) {
        this.talkToNCE = talkToNCE;
    }

    @Override
    public void initialise() {
        super.initialise();
        LOGGER.info("Nce Interface Initialisation");
    }

    @Override
    public void checkInterface() {
        try {
            talkToNCE.sendData(new NceData());
            this.getDccInterfaceStatus().setConnected();
        } catch (ConnectionException exception) {
            this.getDccInterfaceStatus().setDisconnected();
            LOGGER.error(String.format("Connection error: %s", exception.getMessage()), exception);
        }
    }
}
