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
public class DccManufacturerReaderTest {

    private DccManufacturerReader dccManufacturerReader;
    private ResourceFileReader reader;

    @Before
    public void setUp() {
        reader = mock(ResourceFileReader.class);
        dccManufacturerReader = new DccManufacturerReader();
        dccManufacturerReader.setReader(reader);
    }

    @Test
    public void readTest() {
        when(reader.readLine()).thenReturn("17|Advance IC Engineering|US",
                "1|2|3|4", null);
        assertNotNull(dccManufacturerReader.read());
        assertNull(dccManufacturerReader.read());
        assertNull(dccManufacturerReader.read());
    }
}
