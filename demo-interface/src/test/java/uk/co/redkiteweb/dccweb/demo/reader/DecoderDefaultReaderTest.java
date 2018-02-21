package uk.co.redkiteweb.dccweb.demo.reader;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.readers.ResourceFileReader;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 26/07/16.
 */
@RunWith(JUnit4.class)
public class DecoderDefaultReaderTest {

    private DecoderDefaultReader decoderDefaultReader;
    private ResourceFileReader reader;

    @Before
    public void setUp() {
        reader = mock(ResourceFileReader.class);
        decoderDefaultReader = new DecoderDefaultReader();
        decoderDefaultReader.setReader(reader);
    }

    @Test
    public void readTest() {
        when(reader.readLine()).thenReturn("1,1",
                "1,2,3", null);
        assertNotNull(decoderDefaultReader.read());
        assertNull(decoderDefaultReader.read());
        assertNull(decoderDefaultReader.read());
    }

}
