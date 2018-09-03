package uk.co.redkiteweb.dccweb.macros.steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.Cab;
import uk.co.redkiteweb.dccweb.macros.IStep;
import uk.co.redkiteweb.dccweb.services.CabService;

/**
 * Created by shawn on 07/12/16.
 */
@Component("setSpeed")
@Scope("prototype")
public class SetSpeedStep extends AbstractMacroStep implements IStep {

    private CabService cabService;

    @Autowired
    public void setCabService(final CabService cabService) {
        this.cabService = cabService;
    }

    @Override
    public void run() {
        final Cab cab = getCab();
        cab.setSpeed(getValue());
        cabService.updateCab(cab);
    }
}
