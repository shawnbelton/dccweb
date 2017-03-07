package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Macro;
import uk.co.redkiteweb.dccweb.data.repositories.MacroRepository;
import uk.co.redkiteweb.dccweb.services.MacroService;

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
    private MacroService macroService;

    @Before
    public void setup() {
        macroRepository = mock(MacroRepository.class);
        macroService = mock(MacroService.class);
        macros = new Macros();
        macros.setMacroRepository(macroRepository);
        macros.setMacroService(macroService);
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
        verify(macroService, times(1)).runMacro(any(Macro.class));
    }
}
