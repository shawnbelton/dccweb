package uk.co.redkiteweb.dccweb.macros.steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoder;
import uk.co.redkiteweb.dccweb.data.repositories.AccessoryDecoderRepository;

/**
 * Created by shawn on 12/06/17.
 */
@Component("isAccessory")
@Scope("prototype")
public class IsAccessoryStep extends AbstractMacroStep {

    private AccessoryDecoderRepository accessoryDecoderRepository;

    @Autowired
    public void setAccessoryDecoderRepository(final AccessoryDecoderRepository accessoryDecoderRepository) {
        this.accessoryDecoderRepository = accessoryDecoderRepository;
    }

    @Override
    public void run() {
        final AccessoryDecoder accessoryDecoder = accessoryDecoderRepository.findOne(getTargetId());
        getMacroContext().setState(accessoryDecoder.getCurrentValue().equals(getValue()));
    }
}
