package uk.co.redkiteweb.dccweb.macros.steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.macros.MacroContext;
import uk.co.redkiteweb.dccweb.macros.MacroStepItem;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 06/05/17.
 */
@RunWith(JUnit4.class)
public class ThenGoToStepTest {

    private ThenGoToStep thenGoToStep;
    private MacroStepItem macroStep;
    private MacroContext macroContext;

    @Before
    public void setup() {
        macroContext = mock(MacroContext.class);
        macroStep = mock(MacroStepItem.class);
        thenGoToStep = new ThenGoToStep();
        thenGoToStep.setMacroStep(macroStep);
        thenGoToStep.setMacroContext(macroContext);
    }

    @Test
    public void testTrueGoto() {
        when(macroStep.getNumber()).thenReturn(1);
        when(macroStep.getValue()).thenReturn(10);
        when(macroContext.isState()).thenReturn(true);
        assertEquals(new Integer(10), thenGoToStep.runStep());
    }

    @Test
    public void testFalseDoNotGoto() {
        when(macroStep.getNumber()).thenReturn(1);
        when(macroStep.getValue()).thenReturn(10);
        when(macroContext.isState()).thenReturn(false);
        assertEquals(new Integer(2), thenGoToStep.runStep());
    }

}
