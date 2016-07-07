package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.readers.DecoderReader;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 07/07/16.
 */
@RunWith(JUnit4.class)
public class DecodersTest {

    private Decoders decoders;
    private DecoderReader decoderReader;

    @Before
    public void setUp() {
        decoderReader = mock(DecoderReader.class);
        decoders = new Decoders();
        decoders.setDecoderReader(decoderReader);
    }

    @Test
    public void testReadDecoder() {
        when(decoderReader.readDecoderOnProgram()).thenReturn(new Decoder());
        assertNotNull(decoders.readDecoder());
    }

}
