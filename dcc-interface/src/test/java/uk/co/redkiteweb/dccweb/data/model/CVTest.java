package uk.co.redkiteweb.dccweb.data.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 25/07/16.
 */
@RunWith(JUnit4.class)
public class CVTest {

    private CV cv;

    @Before
    public void setUp() {
        cv = new CV();
        cv.setDecoderId(1234);
        cv.setCvNumber(2);
        cv.setCvValue(3);
    }

    @Test
    public void valuesTest() {
        assertEquals(new Integer(1234), cv.getDecoderId());
        assertEquals(new Integer(2), cv.getCvNumber());
        assertEquals(new Integer(3), cv.getCvValue());
    }

}
