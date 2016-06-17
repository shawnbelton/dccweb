package uk.co.redkiteweb.dccweb.nce;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.AbstractDccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;

/**
 * Created by shawn on 15/06/16.
 */
@Component("Nce")
public class NceInterface extends AbstractDccInterface {

    private static final Logger LOGGER = Logger.getLogger(NceInterface.class);

    @Override
    public void initialise() {
        super.initialise();
        LOGGER.info("Nce Interface Initialisation");
    }

    @Override
    public void checkInterface() {

    }
}
