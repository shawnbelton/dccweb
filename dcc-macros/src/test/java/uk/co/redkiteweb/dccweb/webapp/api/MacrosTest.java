package uk.co.redkiteweb.dccweb.webapp.api;

import com.google.common.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Macro;
import uk.co.redkiteweb.dccweb.events.MacroRunEvent;
import uk.co.redkiteweb.dccweb.services.MacroService;

import static org.mockito.Mockito.*;

/**
 * Created by shawn on 06/12/16.
 */
@RunWith(JUnit4.class)
public class MacrosTest {

    private Macros macros;
    private MacroService macroService;
    private EventBus eventBus;

    @Before
    public void setup() {
        macroService = mock(MacroService.class);
        eventBus = mock(EventBus.class);
        macros = new Macros();
        macros.setEventBus(eventBus);
        macros.setMacroService(macroService);
    }

    @Test
    public void testFetchMacro() {
        macros.getMacro(1);
        verify(macroService, times(1)).getMacro(anyInt());
    }

    @Test
    public void testGetMacros() {
        macros.getMacros();
        verify(macroService, times(1)).getMacros();
    }

    @Test
    public void testSaveMacro() {
        macros.saveMacro(new Macro());
        verify(macroService, times(1)).saveMacro(any(Macro.class));
    }

    @Test
    public void testDeleteMacro() {
        macros.deleteMacro(new Macro());
        verify(macroService, times(1)).deleteMacro(any(Macro.class));
    }

    @Test
    public void testRunMacro() {
        macros.runMacro(new Macro());
        verify(eventBus, times(1)).post(any(MacroRunEvent.class));
    }
}
