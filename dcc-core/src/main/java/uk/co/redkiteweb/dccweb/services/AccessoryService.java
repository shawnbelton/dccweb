package uk.co.redkiteweb.dccweb.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uk.co.redkiteweb.dccweb.data.AccessoryOperation;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.messages.OperateAccessoryMessage;

/**
 * Created by shawn on 03/03/17.
 */
@Service
public class AccessoryService {

    private static final Logger LOGGER = LogManager.getLogger(AccessoryService.class);

    private DccInterface dccInterface;

    @Autowired
    public void setDccInterface(final DccInterface dccInterface) {
        this.dccInterface = dccInterface;
    }

    @Async
    public void operateService(final AccessoryOperation accessoryOperation) {
        LOGGER.info(String.format("Setting accessory address %d to %d", accessoryOperation.getAccessoryAddress(), accessoryOperation.getOperationValue()));
        final OperateAccessoryMessage message = new OperateAccessoryMessage();
        message.setAccessoryAddress(accessoryOperation.getAccessoryAddress());
        message.setAccessoryOperation(accessoryOperation.getOperationValue());
        dccInterface.sendMessage(message);
    }

}
