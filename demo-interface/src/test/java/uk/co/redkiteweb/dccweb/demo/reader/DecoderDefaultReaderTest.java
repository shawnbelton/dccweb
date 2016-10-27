package uk.co.redkiteweb.dccweb.demo.reader;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.core.env.Environment;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 26/07/16.
 */
@RunWith(JUnit4.class)
public class DecoderDefaultReaderTest {

    private DecoderDefaultReader decoderDefaultReader;
    private Environment environment;

    @Before
    public void setUp() {
        environment = mock(Environment.class);
        decoderDefaultReader = new DecoderDefaultReader();
        decoderDefaultReader.setEnvironment(environment);
    }

    @Test
    public void readTest() {
        when(environment.getProperty(anyString())).thenReturn("./src/test/resources/testDefault.csv");
        assertNotNull(decoderDefaultReader.read());
        assertNull(decoderDefaultReader.read());
        assertNull(decoderDefaultReader.read());
    }

    @Test
    public void missingFileTest() {
        when(environment.getProperty(anyString())).thenReturn("missing.csv");
        assertNull(decoderDefaultReader.read());
    }
}
