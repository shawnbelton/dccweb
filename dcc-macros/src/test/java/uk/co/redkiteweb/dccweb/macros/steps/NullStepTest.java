package uk.co.redkiteweb.dccweb.macros.steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by shawn on 06/12/16.
 */
@RunWith(JUnit4.class)
public class NullStepTest {

    private NullStep nullStep;

    @Before
    public void setup() {
        this.nullStep = new NullStep();
    }

    @Test
    public void testRun() {
        nullStep.run();
    }
}
