package uk.co.redkiteweb.dccweb.macros.steps;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by shawn on 05/05/17.
 */
@Component("exitMacro")
@Scope("prototype")
public class ExitMacroStep extends AbstractMacroStep {

    @Override
    public void run() {

    }

    @Override
    public Integer getNextStepNumber() {
        return -1;
    }
}

