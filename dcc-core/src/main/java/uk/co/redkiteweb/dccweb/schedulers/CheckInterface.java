package uk.co.redkiteweb.dccweb.schedulers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;

/**
 * Created by shawn on 17/06/16.
 */
@Component
public class CheckInterface {

    private static final Logger LOGGER = Logger.getLogger(CheckInterface.class);

    private DccInterface dccInterface;

    @Autowired
    public void setDccInterface(final DccInterface dccInterface) {
        this.dccInterface = dccInterface;
    }

    @Scheduled(fixedDelay = 5000)
    public void checkInterface() {
        LOGGER.info("Checking Interface.");
        dccInterface.checkInterface();
    }
}
