package uk.co.redkiteweb.dccweb.macros;


/**
 * Created by shawn on 05/12/16.
 */
public interface IStep {
    void setMacroContext(final MacroContext macroContext);
    void setMacroStep(final MacroStepItem macroStep);
    Integer runStep();
    void run();
}
