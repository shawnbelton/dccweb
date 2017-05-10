package uk.co.redkiteweb.dccweb.macros.factory;

import uk.co.redkiteweb.dccweb.data.model.MacroStep;
import uk.co.redkiteweb.dccweb.macros.MacroContext;

/**
 * Created by shawn on 05/12/16.
 */
public interface IStep {
    void setMacroContext(final MacroContext macroContext);
    void setMacroStep(final MacroStep macroStep);
    Integer runStep();
    void run();
}
