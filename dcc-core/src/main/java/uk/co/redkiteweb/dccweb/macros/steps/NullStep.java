package uk.co.redkiteweb.dccweb.macros.steps;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.macros.factory.IStep;

/**
 * Created by shawn on 05/12/16.
 */
@Component("null")
@Scope("prototype")
public class NullStep extends AbstractMacroStep implements IStep {

    @Override
    public void run() {

    }
}
