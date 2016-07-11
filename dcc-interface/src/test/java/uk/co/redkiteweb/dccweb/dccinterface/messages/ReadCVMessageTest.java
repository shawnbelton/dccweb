package uk.co.redkiteweb.dccweb.dccinterface.messages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 11/07/16.
 */
@RunWith(JUnit4.class)
public class ReadCVMessageTest {

    private ReadCVMessage readCVMessage;

    @Before
    public void setUp() {
        readCVMessage = new ReadCVMessage();
        readCVMessage.setCvReg(4);
    }

    @Test
    public void getCVRegTest() {
        assertEquals(4, readCVMessage.getCvReg());
    }
}
