package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderRepository;
import uk.co.redkiteweb.dccweb.readers.DecoderReader;

import java.util.ArrayList;

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
    private DecoderRepository decoderRepository;

    @Before
    public void setUp() {
        decoderReader = mock(DecoderReader.class);
        decoderRepository = mock(DecoderRepository.class);
        decoders = new Decoders();
        decoders.setDecoderReader(decoderReader);
        decoders.setDecoderRepository(decoderRepository);
    }

    @Test
    public void testReadDecoder() {
        when(decoderReader.readDecoderOnProgram()).thenReturn(new Decoder());
        assertNotNull(decoders.readDecoder());
    }

    @Test
    public void testReadAll() {
        when(decoderRepository.findAll()).thenReturn(new ArrayList<Decoder>());
        assertNotNull(decoders.allDecoders());
    }
}
