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
 * Created by shawn on 21/02/17.
 */
@RunWith(JUnit4.class)
public class AccessoryDecoderTypeOperationReaderTest {

    private AccessoryDecoderTypeOperationReader accessoryDecoderTypeOperationReader;
    private ResourceFileReader reader;

    @Before
    public void setup() {
        reader = mock(ResourceFileReader.class);
        accessoryDecoderTypeOperationReader = new AccessoryDecoderTypeOperationReader();
        accessoryDecoderTypeOperationReader.setReader(reader);
    }

    @Test
    public void readTest() {
        when(reader.readLine()).thenReturn("1|1|Stop|0",
                "1|1|0",
                null);
        assertNotNull(accessoryDecoderTypeOperationReader.read());
        assertNull(accessoryDecoderTypeOperationReader.read());
        assertNull(accessoryDecoderTypeOperationReader.read());
    }

}
