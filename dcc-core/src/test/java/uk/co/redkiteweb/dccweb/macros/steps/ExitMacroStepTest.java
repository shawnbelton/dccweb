package uk.co.redkiteweb.dccweb.macros.steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 06/05/17.
 */
@RunWith(JUnit4.class)
public class ExitMacroStepTest {

    private ExitMacroStep exitMacroStep;

    @Before
    public void setup() {
        exitMacroStep = new ExitMacroStep();
    }

    @Test
    public void exitMacoTest() {
        assertEquals(new Integer(-1), exitMacroStep.runStep());
    }

}
