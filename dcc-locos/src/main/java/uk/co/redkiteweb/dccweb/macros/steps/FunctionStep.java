package uk.co.redkiteweb.dccweb.macros.steps;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.Cab;
import uk.co.redkiteweb.dccweb.data.CabFunction;
import uk.co.redkiteweb.dccweb.macros.IStep;

/**
 * Created by shawn on 05/12/16.
 */
@Component("decoderFunction")
@Scope("prototype")
public class FunctionStep extends AbstractCabMacroStep implements IStep {

    @Override
    public void run() {
        final Cab cab = this.getCab();
        for (CabFunction cabFunction : cab.getCabFunctions()) {
            if (cabFunction.getNumber().equals(getMacroStep().getFunctionNumber())) {
                cabFunction.setState(this.getFunctionStatus());
            }
        }
        getCabService().updateCabFunctions(cab);
    }

    private Boolean getFunctionStatus() {
        return "true".equalsIgnoreCase(getMacroStep().getFunctionStatus());
    }
}
