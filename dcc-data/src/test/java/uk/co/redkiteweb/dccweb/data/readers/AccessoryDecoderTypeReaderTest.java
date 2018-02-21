package uk.co.redkiteweb.dccweb.data.readers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 30/06/16.
 */
@RunWith(JUnit4.class)
public class AccessoryDecoderTypeReaderTest {

    private AccessoryDecoderTypeReader accessoryDecoderTypeReader;
    private ResourceFileReader reader;

    @Before
    public void setUp() {
        reader = mock(ResourceFileReader.class);
        accessoryDecoderTypeReader = new AccessoryDecoderTypeReader();
        accessoryDecoderTypeReader.setReader(reader);
    }

    @Test
    public void readTest() {
        when(reader.readLine()).thenReturn("1|Semaphore Signal|SEM_SIG",
                "2|Error|ERROR|Too Many", null);
        assertNotNull(accessoryDecoderTypeReader.read());
        assertNull(accessoryDecoderTypeReader.read());
        assertNull(accessoryDecoderTypeReader.read());
    }
}
