package uk.co.redkiteweb.dccweb.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import uk.co.redkiteweb.dccweb.data.Cab;
import uk.co.redkiteweb.dccweb.data.CabFunction;
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
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    public void setDccInterface(final DccInterface dccInterface) {
        this.dccInterface = dccInterface;
    }

    @Autowired
    public void setMessagingTemplate(final SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void updateCab(final Cab cab) {
        if (hasDecoder(cab)) {
            LOGGER.info(String.format("Updating cab %d", cab.getLoco().getDecoder().getCurrentAddress()));
            final ChangeSpeedMessage changeSpeedMessage = new ChangeSpeedMessage();
            changeSpeedMessage.setAddress(cab.getLoco().getDecoder().getCurrentAddress());
            changeSpeedMessage.setAddressMode(cab.getLoco().getDecoder().getAddressMode());
            changeSpeedMessage.setSpeedSteps(toSpeedSteps(cab.getSteps()));
            changeSpeedMessage.setSpeed(cab.getSpeed());
            changeSpeedMessage.setDirection(toDirection(cab.getDirection()));
            dccInterface.sendMessage(changeSpeedMessage);
            messagingTemplate.convertAndSend("/cab", cab);
        }
    }

    public void updateCabFunctions(final Cab cab) {
        if (hasDecoder(cab)) {
            final UpdateFunctionsMessage updateFunctionsMessage = new UpdateFunctionsMessage();
            updateFunctionsMessage.setAddress(cab.getLoco().getDecoder().getCurrentAddress());
            updateFunctionsMessage.setAddressMode(cab.getLoco().getDecoder().getAddressMode());
            for(CabFunction cabFunction : cab.getCabFunctions()) {
                updateFunctionsMessage.addFunction(cabFunction.getNumber(), cabFunction.getState());
            }
            dccInterface.sendMessage(updateFunctionsMessage);
            messagingTemplate.convertAndSend("/cab", cab);
        }
    }

    private static boolean hasDecoder(final Cab cab) {
        return cab.getLoco() != null && cab.getLoco().getDecoder() != null;
    }

    private static ChangeSpeedMessage.SpeedSteps toSpeedSteps(final String stepsStr) {
        ChangeSpeedMessage.SpeedSteps steps = STEPS_128;
        if ("28".equalsIgnoreCase(stepsStr)) {
            steps = STEPS_28;
        }
        return steps;
    }

    private static ChangeSpeedMessage.Direction toDirection(final String directionStr) {
        ChangeSpeedMessage.Direction direction = UP;
        if ("DOWN".equalsIgnoreCase(directionStr)) {
            direction = DOWN;
        } else if ("RSTOP".equalsIgnoreCase(directionStr)) {
            direction = RSTOP;
        } else if ("FSTOP".equalsIgnoreCase(directionStr)) {
            direction = FSTOP;
        }
        return direction;
    }

}
