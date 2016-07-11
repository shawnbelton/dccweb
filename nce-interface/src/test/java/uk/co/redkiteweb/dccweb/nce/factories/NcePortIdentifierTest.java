package uk.co.redkiteweb.dccweb.nce.factories;

import gnu.io.NoSuchPortException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by shawn on 11/07/16.
 */
@RunWith(JUnit4.class)
public class NcePortIdentifierTest {

    private NcePortIdentifier ncePortIdentifier;

    @Before
    public void setUp() {
        ncePortIdentifier = new NcePortIdentifier();
        ncePortIdentifier.setConnectionName("/dev/ttyS0");
    }

    @Test
    public void getTest() {
        try {
            ncePortIdentifier.getInstance();
        } catch (NoSuchPortException exception) {
            assertTrue(true);
        }
    }
}
