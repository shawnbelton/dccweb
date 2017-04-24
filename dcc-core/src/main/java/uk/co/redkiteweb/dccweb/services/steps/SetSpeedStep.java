package uk.co.redkiteweb.dccweb.services.steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.Cab;
import uk.co.redkiteweb.dccweb.services.CabService;
import uk.co.redkiteweb.dccweb.services.factory.IStep;

/**
 * Created by shawn on 07/12/16.
 */
@Component("setSpeed")
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
