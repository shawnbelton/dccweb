package uk.co.redkiteweb.dccweb.macros.steps;

import uk.co.redkiteweb.dccweb.macros.IStep;
import uk.co.redkiteweb.dccweb.macros.MacroContext;
import uk.co.redkiteweb.dccweb.macros.MacroStepItem;

public abstract class AbstractMacroStep implements IStep {

    private MacroContext macroContext;
    private MacroStepItem macroStep;

    @Override
    public void setMacroContext(final MacroContext macroContext) {
        this.macroContext = macroContext;
    }

    @Override
    public MacroContext getMacroContext() {
        return this.macroContext;
    }

    @Override
    public void setMacroStep(final MacroStepItem macroStep) {
        this.macroStep = macroStep;
    }

    protected MacroStepItem getMacroStep() {
        return this.macroStep;
    }

    Integer getNextStepNumber() {
        return this.macroStep.getNumber() + 1;
    }

    @Override
    public Integer runStep() {
        run();
        return getNextStepNumber();
    }

    public abstract void run();
}
