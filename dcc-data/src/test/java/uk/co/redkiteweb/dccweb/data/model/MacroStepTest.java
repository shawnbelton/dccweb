package uk.co.redkiteweb.dccweb.data.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 06/12/16.
 */
@RunWith(JUnit4.class)
public class MacroStepTest {

    private MacroStep macroStep;

    @Before
    public void setup() {
        macroStep = new MacroStep();
        macroStep.setMacroId(1);
        macroStep.setStepId(1);
        macroStep.setDelay(1.2f);
        macroStep.setFunctionNumber(1);
        macroStep.setNumber(1);
        macroStep.setFunctionStatus("True");
        macroStep.setTrainId(1);
        macroStep.setType("type");
    }

    @Test
    public void testMacroId() {
        assertEquals(new Integer(1), macroStep.getMacroId());
    }

    @Test
    public void testStepId() {
        assertEquals(new Integer(1), macroStep.getStepId());
    }

    @Test
    public void testDelay() {
        assertEquals(new Float(1.2f), macroStep.getDelay());
    }

    @Test
    public void testFunctionNumber() {
        assertEquals(new Integer(1), macroStep.getFunctionNumber());
    }

    @Test
    public void testNumber() {
        assertEquals(new Integer(1), macroStep.getNumber());
    }

    @Test
    public void testFunctionStatus() {
        assertEquals("True", macroStep.getFunctionStatus());
    }

    @Test
    public void testTrainId() {
        assertEquals(new Integer(1), macroStep.getTrainId());
    }

    @Test
    public void testType() {
        assertEquals("type", macroStep.getType());
    }
}
