package uk.co.redkiteweb.dccweb.webapp.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ChangeSpeedMessage;
import uk.co.redkiteweb.dccweb.webapp.data.Cab;

import static uk.co.redkiteweb.dccweb.dccinterface.messages.ChangeSpeedMessage.Direction.DOWN;
import static uk.co.redkiteweb.dccweb.dccinterface.messages.ChangeSpeedMessage.Direction.STOP;
import static uk.co.redkiteweb.dccweb.dccinterface.messages.ChangeSpeedMessage.Direction.UP;
import static uk.co.redkiteweb.dccweb.dccinterface.messages.ChangeSpeedMessage.SpeedSteps.STEPS_128;

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
            changeSpeedMessage.setSpeedSteps(toSpeedSteps());
            changeSpeedMessage.setSpeed(cab.getSpeed());
            changeSpeedMessage.setAddress(cab.getTrain().getDecoder().getCurrentAddress());
            changeSpeedMessage.setDirection(toDirection(cab.getDirection()));
            dccInterface.sendMessage(changeSpeedMessage);
        }
    }

    private static ChangeSpeedMessage.SpeedSteps toSpeedSteps() {
        return STEPS_128;
    }

    private static ChangeSpeedMessage.Direction toDirection(final String directionStr) {
        ChangeSpeedMessage.Direction direction = STOP;
        if ("UP".equalsIgnoreCase(directionStr)) {
            direction = UP;
        } else if ("DOWN".equalsIgnoreCase(directionStr)) {
            direction = DOWN;
        }
        return direction;
    }

}
