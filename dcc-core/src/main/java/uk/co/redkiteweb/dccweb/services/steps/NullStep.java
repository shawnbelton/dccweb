package uk.co.redkiteweb.dccweb.services.steps;

import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.services.factory.IStep;

/**
 * Created by shawn on 05/12/16.
 */
@Component("null")
public class NullStep extends AbstractMacroStep implements IStep {

    @Override
    public void run() {

    }
}