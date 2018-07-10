package uk.co.redkiteweb.dccweb.services;

import com.google.common.eventbus.EventBus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uk.co.redkiteweb.dccweb.data.AccessoryOperation;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoder;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoderType;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoderTypeOperation;
import uk.co.redkiteweb.dccweb.data.repositories.AccessoryDecoderRepository;
import uk.co.redkiteweb.dccweb.data.repositories.AccessoryDecoderTypeRepository;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.messages.OperateAccessoryMessage;
import uk.co.redkiteweb.dccweb.events.AccessoryUpdateEvent;

import java.util.List;

/**
 * Created by shawn on 03/03/17.
 */
@Service
public class AccessoryService {

    private static final Logger LOGGER = LogManager.getLogger(AccessoryService.class);

    private DccInterface dccInterface;
    private AccessoryDecoderRepository accessoryDecoderRepository;
    private AccessoryDecoderTypeRepository accessoryDecoderTypeRepository;
    private EventBus eventBus;

    @Autowired
    public void setDccInterface(final DccInterface dccInterface) {
        this.dccInterface = dccInterface;
    }

    @Autowired
    public void setAccessoryDecoderRepository(final AccessoryDecoderRepository accessoryDecoderRepository) {
        this.accessoryDecoderRepository = accessoryDecoderRepository;
    }

    @Autowired
    public void setAccessoryDecoderTypeRepository(AccessoryDecoderTypeRepository accessoryDecoderTypeRepository) {
        this.accessoryDecoderTypeRepository = accessoryDecoderTypeRepository;
    }

    @Autowired
    public void setEventBus(final EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public List<AccessoryDecoder> getAccessoryDecoders() {
        return (List<AccessoryDecoder>)accessoryDecoderRepository.findAll();
    }

    public List<AccessoryDecoder> saveAccessoryDecoder(final AccessoryDecoder accessoryDecoder) {
        final AccessoryDecoderType accessoryDecoderType = accessoryDecoderTypeRepository.findOne(accessoryDecoder.getAccessoryDecoderType().getDecoderTypeId());
        accessoryDecoder.setAccessoryDecoderType(accessoryDecoderType);
        if (accessoryDecoder.getCurrentValue()==null) {
            accessoryDecoder.setCurrentValue(accessoryDecoderType.getDecoderTypeOperations().get(0).getDecoderOperationValue());
        }
        accessoryDecoderRepository.save(accessoryDecoder);
        return getAccessoryDecoders();
    }

    @Async
    public void operateServiceAsyc(final AccessoryOperation accessoryOperation) {
        operateService(accessoryOperation);
    }

    public void operateService(final AccessoryOperation accessoryOperation) {
        final List<AccessoryDecoder> accessoryDecoders =
                accessoryDecoderRepository.findAccessoryDecodersByAddress(accessoryOperation.getAccessoryAddress());
        final OperateAccessoryMessage message = new OperateAccessoryMessage();
        message.setAccessoryAddress(accessoryOperation.getAccessoryAddress());
        message.setAccessoryOperation(accessoryOperation.getOperationValue());
        final String logMessage = logUpdates(accessoryDecoders, accessoryOperation);
        message.setLogMessage(logMessage);
        LOGGER.info(logMessage);
        dccInterface.sendMessage(message);
        accessoryDecoders.stream().forEach(accessoryDecoder -> operateAccessory(accessoryOperation, accessoryDecoder));
    }

    private void operateAccessory(final AccessoryOperation accessoryOperation, final AccessoryDecoder accessoryDecoder) {
        accessoryDecoder.setCurrentValue(accessoryOperation.getOperationValue());
        accessoryDecoderRepository.save(accessoryDecoder);
        final AccessoryDecoder accessoryDecoder1 = accessoryDecoderRepository.findOne(accessoryDecoder.getAccessoryDecoderId());
        eventBus.post(new AccessoryUpdateEvent(accessoryDecoder1));
    }

    private String logUpdates(final List<AccessoryDecoder> accessoryDecoders, final AccessoryOperation accessoryOperation) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Setting accessory ");
        String seperator = "";
        for(AccessoryDecoder accessoryDecoder : accessoryDecoders) {
            stringBuilder.append(seperator);
            stringBuilder.append(
                    String.format("%s to %s",
                            accessoryDecoder.getName(),
                            getOperationName(accessoryDecoder, accessoryOperation.getOperationValue())));
            seperator = ", ";
        }
        return stringBuilder.toString();
    }

    private String getOperationName(final AccessoryDecoder accessoryDecoder,
                                    final Integer operation) {
        String operationValue = "";
        final AccessoryDecoderType accessoryDecoderType = accessoryDecoder.getAccessoryDecoderType();
        for(AccessoryDecoderTypeOperation accessoryDecoderTypeOperation
                : accessoryDecoderType.getDecoderTypeOperations()) {
            if (accessoryDecoderTypeOperation.getDecoderOperationValue().equals(operation)) {
                operationValue = accessoryDecoderTypeOperation.getDecoderTypeOperation();
            }
        }
        return operationValue;
    }
}
