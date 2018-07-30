package uk.co.redkiteweb.dccweb.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Macro;
import uk.co.redkiteweb.dccweb.data.model.MacroStep;
import uk.co.redkiteweb.dccweb.data.repositories.MacroRepository;
import uk.co.redkiteweb.dccweb.data.repositories.MacroStepRepository;
import uk.co.redkiteweb.dccweb.data.store.LogStore;
import uk.co.redkiteweb.dccweb.events.MacroRunEvent;
import uk.co.redkiteweb.dccweb.macros.factory.IStep;
import uk.co.redkiteweb.dccweb.macros.factory.StepFactory;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

/**
 * Created by shawn on 06/12/16.
 */
@RunWith(JUnit4.class)
public class MacroServiceTest {

    private MacroService macroService;
    private StepFactory stepFactory;
    private MacroRepository macroRepository;
    private MacroStepRepository macroStepRepository;

    @Before
    public void setup() {
        macroRepository = mock(MacroRepository.class);
        macroStepRepository = mock(MacroStepRepository.class);
        stepFactory = mock(StepFactory.class);
        final LogStore logStore = mock(LogStore.class);
        macroService = new MacroService();
        macroService.setMacroRepository(macroRepository);
        macroService.setMacroStepRepository(macroStepRepository);
        macroService.setStepFactory(stepFactory);
        macroService.setLogStore(logStore);
    }

    @Test
    public void runTestNullSteps() {
        final Macro macro = createMacro();
        when(macroStepRepository.getByMacroId(anyInt())).thenReturn(null);
        macroService.runMacroListener(new MacroRunEvent(macro));
        verify(stepFactory, never()).getInstance(any(MacroStep.class));
    }

    @Test
    public void runTestEmptySteps() {
        final Macro macro = createMacro();
        macro.setSteps(new ArrayList<>());
        when(macroStepRepository.getByMacroId(anyInt())).thenReturn(macro.getSteps());
        macroService.runMacroListener(new MacroRunEvent(macro));
        verify(stepFactory, never()).getInstance(any(MacroStep.class));
    }

    @Test
    public void runTestOneStep() {
        final Macro macro = createMacro();
        macro.setSteps(new ArrayList<>());
        final MacroStep step = mock(MacroStep.class);
        when(step.getNumber()).thenReturn(1);
        macro.getSteps().add(step);
        when(macroStepRepository.getByMacroId(anyInt())).thenReturn(macro.getSteps());
        final IStep stepImp = mock(IStep.class);
        when(stepImp.runStep()).thenReturn(2);
        when(stepFactory.getInstance(any(MacroStep.class))).thenReturn(stepImp);
        macroService.runMacroListener(new MacroRunEvent(macro));
        verify(stepImp, times(1)).runStep();
    }

    @Test
    public void testFetchMacro() {
        macroService.getMacro(1);
        verify(macroRepository, times(1)).findOne(anyInt());
    }

    @Test
    public void testGetMacros() {
        macroService.getMacros();
        verify(macroRepository, times(1)).findAll();
    }

    @Test
    public void testSaveMacro() {
        macroService.saveMacro(new Macro());
        verify(macroRepository, times(1)).save(any(Macro.class));
    }

    @Test
    public void testDeleteMacro() {
        macroService.deleteMacro(new Macro());
        verify(macroRepository, times(1)).delete(any(Macro.class));
    }


    private Macro createMacro() {
        final Macro macro = new Macro();
        when(macroRepository.findOne(anyInt())).thenReturn(macro);
        return macro;
    }
}
