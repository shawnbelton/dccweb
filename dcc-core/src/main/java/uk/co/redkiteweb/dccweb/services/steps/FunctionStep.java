package uk.co.redkiteweb.dccweb.services.steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.Cab;
import uk.co.redkiteweb.dccweb.data.CabFunction;
import uk.co.redkiteweb.dccweb.services.CabService;

/**
 * Created by shawn on 05/12/16.
 */
@Component("decoderFunction")
@Scope("prototype")
public class FunctionStep extends AbstractMacroStep implements IStep {

    private CabService cabService;

    @Autowired
    public void setCabService(final CabService cabService) {
        this.cabService = cabService;
    }

    @Override
    public void run() {
        final Cab cab = this.getCab();
        for (CabFunction cabFunction : cab.getCabFunctions()) {
            if (cabFunction.getNumber()==this.getFunctionNumber()) {
                cabFunction.setState(this.getFunctionStatus());
            }
        }
        cabService.updateCabFunctions(cab);
    }
}
