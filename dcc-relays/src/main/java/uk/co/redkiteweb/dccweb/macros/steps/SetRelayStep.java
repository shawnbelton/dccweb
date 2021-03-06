package uk.co.redkiteweb.dccweb.macros.steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.macros.IStep;
import uk.co.redkiteweb.dccweb.services.RelayControllerService;

/**
 * Created by shawn on 12/06/17.
 */
@Component("setRelay")
@Scope("prototype")
public class SetRelayStep extends AbstractMacroStep implements IStep {

    private RelayControllerService relayControllerService;

    @Autowired
    public void setRelayControllerService(final RelayControllerService relayControllerService) {
        this.relayControllerService = relayControllerService;
    }

    @Override
    public void run() {
        if (getMacroStep().getValue() == 0) {
            relayControllerService.unsetRelay(getMacroStep().getBlockId(), getMacroStep().getFunctionNumber());
        } else {
            relayControllerService.setRelay(getMacroStep().getBlockId(), getMacroStep().getFunctionNumber());
        }
    }
}
