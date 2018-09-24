package uk.co.redkiteweb.dccweb.macros.steps;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.Cab;
import uk.co.redkiteweb.dccweb.macros.IStep;

/**
 * Created by shawn on 07/12/16.
 */
@Component("setSpeed")
@Scope("prototype")
public class SetSpeedStep extends AbstractCabMacroStep implements IStep {

    @Override
    public void run() {
        final Cab cab = getCab();
        cab.setSpeed(getMacroStep().getValue());
        getCabService().updateCab(cab);
    }

}
