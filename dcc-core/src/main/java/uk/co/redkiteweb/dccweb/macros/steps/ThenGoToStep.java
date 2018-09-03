package uk.co.redkiteweb.dccweb.macros.steps;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.macros.IStep;

/**
 * Created by shawn on 05/05/17.
 */
@Component("thenGoTo")
@Scope("prototype")
public class ThenGoToStep extends AbstractMacroStep implements IStep {

    @Override
    public void run() {
        // This is a controlling step and only controls
        // which is the next step to execute
    }

    @Override
    final Integer getNextStepNumber() {
        Integer nextStep;
        if (getMacroContext().isState()) {
            nextStep = getValue();
        } else {
            nextStep = super.getNextStepNumber();
        }
        return nextStep;
    }
}
