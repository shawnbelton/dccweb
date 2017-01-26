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
public class AccessoryDecoderTypeReaderTest {

    private AccessoryDecoderTypeReader accessoryDecoderTypeReader;

    @Before
    public void setUp() {
        accessoryDecoderTypeReader = new AccessoryDecoderTypeReader();
    }

    @Test
    public void readTest() {
        assertNotNull(accessoryDecoderTypeReader.read());
        assertNull(accessoryDecoderTypeReader.read());
        assertNull(accessoryDecoderTypeReader.read());
    }
}
