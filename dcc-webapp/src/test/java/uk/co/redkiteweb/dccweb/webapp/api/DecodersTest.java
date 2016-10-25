package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.model.DecoderFunction;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderFunctionRepository;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderRepository;
import uk.co.redkiteweb.dccweb.readers.DecoderReader;

import java.util.ArrayList;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 07/07/16.
 */
@RunWith(JUnit4.class)
public class DecodersTest {

    private Decoders decoders;
    private DecoderReader decoderReader;
    private DecoderRepository decoderRepository;
    private DecoderFunctionRepository decoderFunctionRepository;

    @Before
    public void setUp() {
        decoderReader = mock(DecoderReader.class);
        decoderRepository = mock(DecoderRepository.class);
        decoderFunctionRepository = mock(DecoderFunctionRepository.class);
        decoders = new Decoders();
        decoders.setDecoderReader(decoderReader);
        decoders.setDecoderRepository(decoderRepository);
        decoders.setDecoderFunctionRepository(decoderFunctionRepository);
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

    @Test
    public void getById() {
        when(decoderRepository.findOne(anyInt())).thenReturn(new Decoder());
        assertNotNull(decoders.getById(1));
    }

    @Test
    public void testAddFunction() {
        final DecoderFunction decoderFunction = new DecoderFunction();
        decoderFunction.setDecoderId(1);
        decoderFunction.setNumber(1);
        decoderFunction.setName("Sound");
        when(decoderRepository.findOne(anyInt())).thenReturn(new Decoder());
        assertNotNull(decoders.addFunction(decoderFunction));
        verify(decoderFunctionRepository, times(1)).save(any(DecoderFunction.class));
    }
}
