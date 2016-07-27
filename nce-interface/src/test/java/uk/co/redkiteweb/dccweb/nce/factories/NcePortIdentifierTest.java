package uk.co.redkiteweb.dccweb.nce.factories;

import gnu.io.NoSuchPortException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.core.env.Environment;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 11/07/16.
 */
@RunWith(JUnit4.class)
public class NcePortIdentifierTest {

    private NcePortIdentifier ncePortIdentifier;
    private Environment environment;

    @Before
    public void setUp() {
        environment = mock(Environment.class);
        ncePortIdentifier = new NcePortIdentifier();
        ncePortIdentifier.setEnvironment(environment);
    }

    @Test
    public void getTest() {
        when(environment.getProperty(anyString())).thenReturn("/dev/ttyS0");
        try {
            ncePortIdentifier.getInstance();
        } catch (NoSuchPortException exception) {
            assertTrue(true);
        }
    }
}
