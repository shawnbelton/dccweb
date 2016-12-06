package uk.co.redkiteweb.dccweb.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Macro;
import uk.co.redkiteweb.dccweb.data.model.MacroStep;
import uk.co.redkiteweb.dccweb.services.factory.StepFactory;
import uk.co.redkiteweb.dccweb.services.steps.IStep;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 06/12/16.
 */
@RunWith(JUnit4.class)
public class MacroServiceTest {

    private MacroService macroService;
    private StepFactory stepFactory;

    @Before
    public void setup() {
        stepFactory = mock(StepFactory.class);
        macroService = new MacroService();
        macroService.setStepFactory(stepFactory);
    }

    @Test
    public void runTestNullSteps() {
        final Macro macro = new Macro();
        macroService.runMacro(macro);
        verify(stepFactory, never()).getInstance(any(MacroStep.class));
    }

    @Test
    public void runTestEmptySteps() {
        final Macro macro = new Macro();
        macro.setSteps(new ArrayList<MacroStep>());
        macroService.runMacro(macro);
        verify(stepFactory, never()).getInstance(any(MacroStep.class));
    }

    @Test
    public void runTestOneStep() {
        final Macro macro = new Macro();
        macro.setSteps(new ArrayList<MacroStep>());
        final MacroStep step = mock(MacroStep.class);
        when(step.getNumber()).thenReturn(1);
        macro.getSteps().add(step);
        final IStep stepImp = mock(IStep.class);
        when(stepFactory.getInstance(any(MacroStep.class))).thenReturn(stepImp);
        macroService.runMacro(macro);
        verify(stepImp, times(1)).run();
    }
}
