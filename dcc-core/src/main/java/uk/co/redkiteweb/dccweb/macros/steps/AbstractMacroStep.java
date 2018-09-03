package uk.co.redkiteweb.dccweb.macros.steps;

import org.springframework.beans.factory.annotation.Autowired;
import uk.co.redkiteweb.dccweb.data.Cab;
import uk.co.redkiteweb.dccweb.data.store.CabStore;
import uk.co.redkiteweb.dccweb.macros.IStep;
import uk.co.redkiteweb.dccweb.macros.MacroContext;
import uk.co.redkiteweb.dccweb.macros.MacroStepItem;

/**
 * Created by shawn on 05/12/16.
 */
public abstract class AbstractMacroStep implements IStep {

    private MacroStepItem macroStep;
    private CabStore cabStore;
    private MacroContext macroContext;

    @Autowired
    public void setCabStore(final CabStore cabStore) {
        this.cabStore = cabStore;
    }

    @Override
    public void setMacroStep(final MacroStepItem macroStep) {
        this.macroStep = macroStep;
    }

    MacroContext getMacroContext() {
        return macroContext;
    }

    @Override
    public void setMacroContext(final MacroContext macroContext) {
        this.macroContext = macroContext;
    }

    Float delay() {
        return macroStep.getDelay();
    }

    Cab getCab() {
        return cabStore.getCab(macroStep.getTargetId());
    }

    Integer getFunctionNumber() {
        return this.macroStep.getFunctionNumber();
    }

    Boolean getFunctionStatus() {
        return "true".equalsIgnoreCase(this.macroStep.getFunctionStatus());
    }

    Integer getTargetId() {
        return macroStep.getTargetId();
    }

    String getBlockId() {
        return macroStep.getBlockId();
    }

    Integer getValue() {
        return this.macroStep.getValue();
    }

    Integer getNextStepNumber() {
        return this.macroStep.getNumber() + 1;
    }

    @Override
    public Integer runStep() {
        run();
        return getNextStepNumber();
    }

}
