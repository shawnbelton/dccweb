package uk.co.redkiteweb.dccweb.data.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 08/08/16.
 */
@RunWith(JUnit4.class)
public class CVPKTest {

    private CVPK cvpk;
    private CVPK cvpk2;

    @Before
    public void setUp() {
        cvpk = new CVPK();
        cvpk.setDecoderId(1);
        cvpk.setCvNumber(2);
        cvpk2 = new CVPK(2,1);
    }

    @Test
    public void testDecoderId() {
        assertEquals(new Integer(1), cvpk.getDecoderId());
        assertEquals(new Integer(2), cvpk2.getDecoderId());
    }

    @Test
    public void testCVNumber() {
        assertEquals(new Integer(2), cvpk.getCvNumber());
        assertEquals(new Integer(1), cvpk2.getCvNumber());
    }
}
