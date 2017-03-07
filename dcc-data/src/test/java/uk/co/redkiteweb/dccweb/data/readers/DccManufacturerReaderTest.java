package uk.co.redkiteweb.dccweb.data.readers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by shawn on 30/06/16.
 */
@RunWith(JUnit4.class)
public class DccManufacturerReaderTest {

    private DccManufacturerReader dccManufacturerReader;

    @Before
    public void setUp() {
        dccManufacturerReader = new DccManufacturerReader();
    }

    @Test
    public void readTest() {
        assertNotNull(dccManufacturerReader.read());
        assertNull(dccManufacturerReader.read());
        assertNull(dccManufacturerReader.read());
    }
}
