package uk.co.redkiteweb.dccweb.services.factory;

import uk.co.redkiteweb.dccweb.data.model.MacroStep;

/**
 * Created by shawn on 05/12/16.
 */
public interface IStep {
    void setMacroStep(final MacroStep macroStep);
    void run();
}
