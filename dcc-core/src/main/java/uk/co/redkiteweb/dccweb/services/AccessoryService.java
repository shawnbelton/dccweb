package uk.co.redkiteweb.dccweb.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.co.redkiteweb.dccweb.data.AccessoryOperation;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoder;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoderType;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoderTypeOperation;
import uk.co.redkiteweb.dccweb.data.repositories.AccessoryDecoderRepository;
import uk.co.redkiteweb.dccweb.data.repositories.AccessoryDecoderTypeRepository;
import uk.co.redkiteweb.dccweb.data.service.NotificationService;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.messages.OperateAccessoryMessage;

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
    private NotificationService notificationService;
    private MacroService macroService;

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
    public void setNotificationService(final NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Autowired
    public void setMacroService(final MacroService macroService) {
        this.macroService = macroService;
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
    @Transactional
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
        for (AccessoryDecoder accessoryDecoder : accessoryDecoders) {
            accessoryDecoder.setCurrentValue(accessoryOperation.getOperationValue());
            accessoryDecoderRepository.save(accessoryDecoder);
            if (accessoryDecoder.getMacro()!=null) {
                macroService.runMacro(accessoryDecoder.getMacro());
            }
        }
        notificationService.createNotification("ACCESSORY", accessoryOperation.getAccessoryAddress().toString());
    }

    private static String logUpdates(final List<AccessoryDecoder> accessoryDecoders, final AccessoryOperation accessoryOperation) {
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

    private static String getOperationName(final AccessoryDecoder accessoryDecoder,
                                    final Integer operation) {
        String operationValue = "";
        for(AccessoryDecoderTypeOperation accessoryDecoderTypeOperation
                : accessoryDecoder.getAccessoryDecoderType().getDecoderTypeOperations()) {
            if (accessoryDecoderTypeOperation.getDecoderOperationValue().equals(operation)) {
                operationValue = accessoryDecoderTypeOperation.getDecoderTypeOperation();
            }
        }
        return operationValue;
    }
}
