package uk.co.redkiteweb.dccweb.data.readers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by shawn on 21/02/17.
 */
@RunWith(JUnit4.class)
public class AccessoryDecoderTypeOperationReaderTest {

    private AccessoryDecoderTypeOperationReader accessoryDecoderTypeOperationReader;

    @Before
    public void setup() {
        accessoryDecoderTypeOperationReader = new AccessoryDecoderTypeOperationReader();
    }

    @Test
    public void readTest() {
        assertNotNull(accessoryDecoderTypeOperationReader.read());
        assertNull(accessoryDecoderTypeOperationReader.read());
        assertNull(accessoryDecoderTypeOperationReader.read());
    }

}
