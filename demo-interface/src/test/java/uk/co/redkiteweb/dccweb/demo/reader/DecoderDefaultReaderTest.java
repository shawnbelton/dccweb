package uk.co.redkiteweb.dccweb.demo.reader;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by shawn on 26/07/16.
 */
@RunWith(JUnit4.class)
public class DecoderDefaultReaderTest {

    private DecoderDefaultReader decoderDefaultReader;

    @Before
    public void setUp() {
        decoderDefaultReader = new DecoderDefaultReader();
    }

    @Test
    public void readTest() {
        assertNotNull(decoderDefaultReader.read());
        assertNull(decoderDefaultReader.read());
        assertNull(decoderDefaultReader.read());
    }

}
