package uk.co.redkiteweb.dccweb.macros.steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.macros.MacroStepItem;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 06/12/16.
 */
@RunWith(JUnit4.class)
public class WaitTest {

    private Wait wait;
    private MacroStepItem step;

    @Before
    public void setup() {
        step = mock(MacroStepItem.class);
        wait = new Wait();
        wait.setMacroStep(step);
    }

    @Test
    public void runTest() {
        when(step.getDelay()).thenReturn(0.0f);
        wait.run();
        assertTrue(true);
    }

    @Test
    public void runWithInterruptionTest() {
        when(step.getDelay()).thenReturn(10f);
        Thread.currentThread().interrupt();
        wait.run();
        Thread.interrupted();
        assertTrue(true);
    }
}
