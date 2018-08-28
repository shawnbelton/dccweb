package uk.co.redkiteweb.dccweb.services;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.redkiteweb.dccweb.data.model.Macro;
import uk.co.redkiteweb.dccweb.data.model.MacroStep;
import uk.co.redkiteweb.dccweb.data.repositories.MacroRepository;
import uk.co.redkiteweb.dccweb.data.repositories.MacroStepRepository;
import uk.co.redkiteweb.dccweb.data.store.LogStore;
import uk.co.redkiteweb.dccweb.events.RunMacroEvent;
import uk.co.redkiteweb.dccweb.macros.MacroContext;
import uk.co.redkiteweb.dccweb.macros.factory.IStep;
import uk.co.redkiteweb.dccweb.macros.factory.StepFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public void setEventBus(final EventBus eventBus) {
        eventBus.register(this);
    }

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

    public Collection<Macro> getMacros() {
        return (Collection<Macro>)this.macroRepository.findAll();
    }

    public Macro getMacro(final Integer macroId) {
        return this.macroRepository.findOne(macroId);
    }


    public Collection<Macro> saveMacro(final Macro macro) {
        this.macroRepository.save(macro);
        return getMacros();
    }

    public Collection<Macro> deleteMacro(final Macro macro) {
        this.macroRepository.delete(macro);
        return getMacros();
    }


    private void runMacro(final Macro macro) {
        final MacroContext macroContext = new MacroContext();
        this.logStore.log("info", String.format("Running macro %s",macro.getName()));
        final List<MacroStep> macroSteps = macroStepRepository.getByMacroId(macro.getMacroId());
        final Map<Integer, MacroStep> steps = orderSteps(macroSteps);
        Integer stepNumber = 1;
        MacroStep step = steps.get(stepNumber);
        while(step!=null) {
            stepNumber = runStep(step, macroContext);
            step = steps.get(stepNumber);
        }
    }

    @Subscribe public void runMacroListener(final RunMacroEvent event) {
        final Macro macro = event.getMacro();
        if (macro!=null) {
            runMacro(macro);
        }
    }

    private static Map<Integer, MacroStep> orderSteps(final List<MacroStep> unordered) {
        final Map<Integer, MacroStep> steps = new HashMap<>();
        if (unordered != null) {
            for (MacroStep step : unordered) {
                steps.put(step.getNumber(), step);
            }
        }
        return steps;
    }

    private Integer runStep(final MacroStep step, final MacroContext macroContext) {
        final IStep stepImp = this.stepFactory.getInstance(step);
        stepImp.setMacroContext(macroContext);
        LOGGER.info(String.format("Running macro step %d", step.getNumber()));
        return stepImp.runStep();
    }
}
