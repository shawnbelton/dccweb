package uk.co.redkiteweb.dccweb.macros.steps;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by shawn on 05/05/17.
 */
@Component("thenGoTo")
@Scope("prototype")
public class ThenGoToStep extends AbstractMacroStep {

    @Override
    public void run() {

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
