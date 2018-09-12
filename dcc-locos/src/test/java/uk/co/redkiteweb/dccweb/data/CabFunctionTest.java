package uk.co.redkiteweb.dccweb.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 28/10/16.
 */
@RunWith(JUnit4.class)
public class CabFunctionTest {

    private CabFunction cabFunction;

    @Before
    public void setup() {
        cabFunction = new CabFunction();
        cabFunction.setName("Sound");
        cabFunction.setNumber(1);
        cabFunction.setState(Boolean.FALSE);
    }

    @Test
    public void testName() {
        assertEquals("Sound", cabFunction.getName());
    }

    @Test
    public void testNumber() {
        assertEquals(new Integer(1), cabFunction.getNumber());
    }

    @Test
    public void testState() {
        assertEquals(Boolean.FALSE, cabFunction.getState());
    }
}
