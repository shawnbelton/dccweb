package uk.co.redkiteweb.dccweb.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uk.co.redkiteweb.dccweb.data.model.Macro;
import uk.co.redkiteweb.dccweb.data.model.MacroStep;
import uk.co.redkiteweb.dccweb.data.model.comparators.MacroStepComparator;
import uk.co.redkiteweb.dccweb.data.repositories.MacroRepository;
import uk.co.redkiteweb.dccweb.data.repositories.MacroStepRepository;
import uk.co.redkiteweb.dccweb.data.store.LogStore;
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

    private LogStore logStore;
    private StepFactory stepFactory;
    private MacroRepository macroRepository;
    private MacroStepRepository macroStepRepository;

    @Autowired
    public void setStepFactory(final StepFactory stepFactory) {
        this.stepFactory = stepFactory;
    }

    @Autowired
    public void setMacroRepository(final MacroRepository macroRepository) {
        this.macroRepository = macroRepository;
    }

    @Autowired
    public void setMacroStepRepository(final MacroStepRepository macroStepRepository) {
        this.macroStepRepository = macroStepRepository;
    }

    @Autowired
    public void setLogStore(final LogStore logStore) {
        this.logStore = logStore;
    }

    public void runMacroByName(final String name) {
        this.runMacro(macroRepository.findByName(name));
    }

    @Async
    public void runMacro(final Macro macro) {
        final List<MacroStep> macroSteps = macroStepRepository.getByMacroId(macro.getMacroId());
        for(MacroStep step : orderSteps(macroSteps)) {
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
        final String logMessage = String.format("Running macro step %d", step.getNumber());
        LOGGER.info(logMessage);
        this.logStore.log("info", logMessage);
        this.stepFactory.getInstance(step).run();
    }
}
