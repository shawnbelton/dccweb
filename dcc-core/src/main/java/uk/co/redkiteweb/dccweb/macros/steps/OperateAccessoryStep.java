package uk.co.redkiteweb.dccweb.macros.steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.AccessoryOperation;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoder;
import uk.co.redkiteweb.dccweb.data.repositories.AccessoryDecoderRepository;
import uk.co.redkiteweb.dccweb.macros.IStep;
import uk.co.redkiteweb.dccweb.services.AccessoryService;

/**
 * Created by shawn on 24/04/17.
 */
@Component("setAccessory")
@Scope("prototype")
public class OperateAccessoryStep extends AbstractMacroStep implements IStep {

    private AccessoryService accessoryService;
    private AccessoryDecoderRepository accessoryDecoderRepository;

    @Autowired
    public void setAccessoryService(final AccessoryService accessoryService) {
        this.accessoryService = accessoryService;
    }

    @Autowired
    public void setAccessoryDecoderRepository(final AccessoryDecoderRepository accessoryDecoderRepository) {
        this.accessoryDecoderRepository = accessoryDecoderRepository;
    }

    @Override
    public void run() {
        final AccessoryDecoder accessoryDecoder = accessoryDecoderRepository.findOne(getTargetId());
        final AccessoryOperation accessoryOperation = new AccessoryOperation();
        accessoryOperation.setAccessoryAddress(accessoryDecoder.getAddress());
        accessoryOperation.setOperationValue(getValue());
        accessoryService.operateServiceAsyc(accessoryOperation);
    }

}
