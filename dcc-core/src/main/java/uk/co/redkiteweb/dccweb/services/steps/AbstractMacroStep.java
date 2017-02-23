package uk.co.redkiteweb.dccweb.services.steps;

import org.springframework.beans.factory.annotation.Autowired;
import uk.co.redkiteweb.dccweb.data.Cab;
import uk.co.redkiteweb.dccweb.data.model.MacroStep;
import uk.co.redkiteweb.dccweb.data.store.CabStore;
import uk.co.redkiteweb.dccweb.services.factory.IStep;

/**
 * Created by shawn on 05/12/16.
 */
public abstract class AbstractMacroStep implements IStep {

    private MacroStep macroStep;
    private CabStore cabStore;

    @Autowired
    public void setCabStore(final CabStore cabStore) {
        this.cabStore = cabStore;
    }

    @Override
    public void setMacroStep(final MacroStep macroStep) {
        this.macroStep = macroStep;
    }

    Float delay() {
        return macroStep.getDelay();
    }

    Cab getCab() {
        return cabStore.getCab(macroStep.getTrainId());
    }

    Integer getFunctionNumber() {
        return this.macroStep.getFunctionNumber();
    }

    Boolean getFunctionStatus() {
        return "true".equalsIgnoreCase(this.macroStep.getFunctionStatus());
    }

    Integer getSpeed() {
        return this.macroStep.getSpeed();
    }

    @Override
    public abstract void run();
}
