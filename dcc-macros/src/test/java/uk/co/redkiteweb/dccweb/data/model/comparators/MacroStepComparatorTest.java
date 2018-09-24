package uk.co.redkiteweb.dccweb.data.model.comparators;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.MacroStep;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 06/12/16.
 */
@RunWith(JUnit4.class)
public class MacroStepComparatorTest {

    private MacroStepComparator macroStepComparator;

    @Before
    public void setup() {
        this.macroStepComparator = new MacroStepComparator();
    }

    @Test
    public void testBothNull() {
        assertEquals(0, macroStepComparator.compare(null, null));
    }

    @Test
    public void testFirstNullSecondNotNull() {
        assertEquals(-1, macroStepComparator.compare(null, new MacroStep()));
    }

    @Test
    public void testFirstNotNullSecondNull() {
        assertEquals(1, macroStepComparator.compare(new MacroStep(), null));
    }

    @Test
    public void testBothNotNull() {
        assertEquals(0, macroStepComparator.compare(new MacroStep(), new MacroStep()));
    }

    @Test
    public void testNumberOneNullNumberTwoNotNull() {
        final MacroStep secondStep = new MacroStep();
        secondStep.setNumber(1);
        assertEquals(-1, macroStepComparator.compare(new MacroStep(), secondStep));
    }

    @Test
    public void testNumberOneNotNullNumberTwoNull() {
        final MacroStep firstStep = new MacroStep();
        firstStep.setNumber(1);
        assertEquals(1, macroStepComparator.compare(firstStep, new MacroStep()));
    }

    @Test
    public void testBothNumbersNotNull() {
        final MacroStep firstStep = new MacroStep();
        firstStep.setNumber(1);
        final MacroStep secondStep = new MacroStep();
        secondStep.setNumber(1);
        assertEquals(0, macroStepComparator.compare(firstStep, secondStep));
    }
}
