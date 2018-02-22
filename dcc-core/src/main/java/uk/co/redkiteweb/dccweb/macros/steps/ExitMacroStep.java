package uk.co.redkiteweb.dccweb.macros.steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.store.LogStore;

/**
 * Created by shawn on 05/05/17.
 */
@Component("exitMacro")
@Scope("prototype")
public class ExitMacroStep extends AbstractMacroStep {

    private LogStore logStore;

    @Autowired
    public void setLogStore(final LogStore logStore) {
        this.logStore = logStore;
    }

    @Override
    public void run() {
        logStore.log("info", "Exiting Macro");
    }

    @Override
    public Integer getNextStepNumber() {
        return -1;
    }
}

