package uk.co.redkiteweb.dccweb.webapp.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ChangeSpeedMessage;
import uk.co.redkiteweb.dccweb.webapp.data.Cab;

import static uk.co.redkiteweb.dccweb.dccinterface.messages.ChangeSpeedMessage.Direction.*;
import static uk.co.redkiteweb.dccweb.dccinterface.messages.ChangeSpeedMessage.SpeedSteps.STEPS_128;
import static uk.co.redkiteweb.dccweb.dccinterface.messages.ChangeSpeedMessage.SpeedSteps.STEPS_28;

/**
 * Created by shawn on 13/09/16.
 */
@Service
public class CabService {

    private static final Logger LOGGER = LogManager.getLogger(CabService.class);

    private DccInterface dccInterface;

    @Autowired
    public void setDccInterface(final DccInterface dccInterface) {
        this.dccInterface = dccInterface;
    }

    @Async
    public void updateCab(final Cab cab) {
        if (cab.getTrain() != null && cab.getTrain().getDecoder() != null) {
            LOGGER.info(String.format("Updating cab %d", cab.getTrain().getDecoder().getCurrentAddress()));
            final ChangeSpeedMessage changeSpeedMessage = new ChangeSpeedMessage();
            changeSpeedMessage.setSpeedSteps(toSpeedSteps(cab.getSteps()));
            changeSpeedMessage.setSpeed(cab.getSpeed());
            changeSpeedMessage.setAddress(cab.getTrain().getDecoder().getCurrentAddress());
            changeSpeedMessage.setDirection(toDirection(cab.getDirection()));
            dccInterface.sendMessage(changeSpeedMessage);
        }
    }

    private static ChangeSpeedMessage.SpeedSteps toSpeedSteps(final String stepsStr) {
        ChangeSpeedMessage.SpeedSteps steps = STEPS_128;
        if ("128".equalsIgnoreCase(stepsStr)) {
            steps = STEPS_128;
        } else if ("28".equalsIgnoreCase(stepsStr)) {
            steps = STEPS_28;
        }
        return steps;
    }

    private static ChangeSpeedMessage.Direction toDirection(final String directionStr) {
        ChangeSpeedMessage.Direction direction = RSTOP;
        if ("UP".equalsIgnoreCase(directionStr)) {
            direction = UP;
        } else if ("DOWN".equalsIgnoreCase(directionStr)) {
            direction = DOWN;
        } else if ("RSTOP".equalsIgnoreCase(directionStr)) {
            direction = RSTOP;
        } else if ("FSTOP".equalsIgnoreCase(directionStr)) {
            direction = FSTOP;
        }
        return direction;
    }

}
