package uk.co.redkiteweb.dccweb.macros;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertTrue;

/**
 * Created by shawn on 06/05/17.
 */
@RunWith(JUnit4.class)
public class MacroContextTest {

    private MacroContext macroContext;

    @Before
    public void setup() {
        macroContext = new MacroContext();
        macroContext.setState(true);
    }

    @Test
    public void testState() {
        assertTrue(macroContext.isState());
    }
}
