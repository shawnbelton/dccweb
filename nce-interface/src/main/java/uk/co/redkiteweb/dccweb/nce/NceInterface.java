package uk.co.redkiteweb.dccweb.nce;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccInterface.DccInterface;

/**
 * Created by shawn on 15/06/16.
 */
@Component("Nce")
public class NceInterface implements DccInterface {

    private static final Logger LOGGER = Logger.getLogger(NceInterface.class);

    @Override
    public void initialise() {
        LOGGER.info("Nce Interface Initialisation");
    }
}
