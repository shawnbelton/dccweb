package uk.co.redkiteweb.dccweb.webapp.api;

import com.google.common.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Macro;
import uk.co.redkiteweb.dccweb.data.repositories.MacroRepository;
import uk.co.redkiteweb.dccweb.events.MacroRunEvent;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 06/12/16.
 */
@RunWith(JUnit4.class)
public class MacrosTest {

    private Macros macros;
    private MacroRepository macroRepository;
    private EventBus eventBus;

    @Before
    public void setup() {
        macroRepository = mock(MacroRepository.class);
        eventBus = mock(EventBus.class);
        macros = new Macros();
        macros.setMacroRepository(macroRepository);
        macros.setEventBus(eventBus);
        when(macroRepository.findAll()).thenReturn(new ArrayList<Macro>());
    }

    @Test
    public void testFetchMacro() {
        when(macroRepository.findOne(anyInt())).thenReturn(new Macro());
        assertNotNull(macros.getMacro(1));
    }

    @Test
    public void testGetMacros() {
        assertNotNull(macros.getMacros());
    }

    @Test
    public void testSaveMacro() {
        macros.saveMacro(new Macro());
        verify(macroRepository, times(1)).save(any(Macro.class));
    }

    @Test
    public void testDeleteMacro() {
        macros.deleteMacro(new Macro());
        verify(macroRepository, times(1)).delete(any(Macro.class));
    }

    @Test
    public void testRunMacro() {
        macros.runMacro(new Macro());
        verify(eventBus, times(1)).post(any(MacroRunEvent.class));
    }
}
