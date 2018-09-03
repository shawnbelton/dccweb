package uk.co.redkiteweb.dccweb.data.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by shawn on 06/12/16.
 */
@RunWith(JUnit4.class)
public class MacroTest {

    private Macro macro;

    @Before
    public void setup() {
        macro = new Macro();
        macro.setMacroId(1);
        macro.setName("Test");
        macro.setSteps(new ArrayList<MacroStep>());
    }

    @Test
    public void testMacroId() {
        assertEquals(new Integer(1), macro.getMacroId());
    }

    @Test
    public void testName() {
        assertEquals("Test", macro.getName());
    }

    @Test
    public void testSteps() {
        assertNotNull(macro.getSteps());
    }
}
