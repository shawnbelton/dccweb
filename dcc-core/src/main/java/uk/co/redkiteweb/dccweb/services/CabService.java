package uk.co.redkiteweb.dccweb.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.redkiteweb.dccweb.data.Cab;
import uk.co.redkiteweb.dccweb.data.CabFunction;
import uk.co.redkiteweb.dccweb.data.service.NotificationService;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ChangeSpeedMessage;
import uk.co.redkiteweb.dccweb.dccinterface.messages.UpdateFunctionsMessage;

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
    private NotificationService notificationService;

    @Autowired
    public void setDccInterface(final DccInterface dccInterface) {
        this.dccInterface = dccInterface;
    }

    @Autowired
    public void setNotificationService(final NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void updateCab(final Cab cab) {
        if (hasDecoder(cab)) {
            LOGGER.info(String.format("Updating cab %d", cab.getTrain().getDecoder().getCurrentAddress()));
            final ChangeSpeedMessage changeSpeedMessage = new ChangeSpeedMessage();
            changeSpeedMessage.setAddress(cab.getTrain().getDecoder().getCurrentAddress());
            changeSpeedMessage.setAddressMode(cab.getTrain().getDecoder().getAddressMode());
            changeSpeedMessage.setSpeedSteps(toSpeedSteps(cab.getSteps()));
            changeSpeedMessage.setSpeed(cab.getSpeed());
            changeSpeedMessage.setDirection(toDirection(cab.getDirection()));
            dccInterface.sendMessage(changeSpeedMessage);
            notificationService.createNotification("CAB", String.format("%d", cab.getTrain().getTrainId()));
        }
    }

    public void updateCabFunctions(final Cab cab) {
        if (hasDecoder(cab)) {
            final UpdateFunctionsMessage updateFunctionsMessage = new UpdateFunctionsMessage();
            updateFunctionsMessage.setAddress(cab.getTrain().getDecoder().getCurrentAddress());
            updateFunctionsMessage.setAddressMode(cab.getTrain().getDecoder().getAddressMode());
            for(CabFunction cabFunction : cab.getCabFunctions()) {
                updateFunctionsMessage.addFunction(cabFunction.getNumber(), cabFunction.getState());
            }
            dccInterface.sendMessage(updateFunctionsMessage);
            notificationService.createNotification("CAB", String.format("%d", cab.getTrain().getTrainId()));
        }
    }

    private static boolean hasDecoder(final Cab cab) {
        return cab.getTrain() != null && cab.getTrain().getDecoder() != null;
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
