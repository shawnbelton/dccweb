package uk.co.redkiteweb.dccweb.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uk.co.redkiteweb.dccweb.data.model.Macro;
import uk.co.redkiteweb.dccweb.data.model.MacroStep;
import uk.co.redkiteweb.dccweb.data.model.comparators.MacroStepComparator;
import uk.co.redkiteweb.dccweb.services.factory.StepFactory;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * Created by shawn on 05/12/16.
 */
@Service
public class MacroService {

    private static final Logger LOGGER = LogManager.getLogger(MacroService.class);

    private StepFactory stepFactory;

    @Autowired
    public void setStepFactory(final StepFactory stepFactory) {
        this.stepFactory = stepFactory;
    }

    @Async
    public void runMacro(final Macro macro) {
        for(MacroStep step : orderSteps(macro.getSteps())) {
            runStep(step);
        }
    }

    private static Set<MacroStep> orderSteps(final List<MacroStep> unordered) {
        final Set<MacroStep> ordered = new TreeSet<MacroStep>(new MacroStepComparator());
        if (unordered != null) {
            ordered.addAll(unordered);
        }
        return ordered;
    }

    private void runStep(final MacroStep step) {
        LOGGER.info(String.format("Running macro step %d", step.getNumber()));
        this.stepFactory.getInstance(step).run();
    }
}
