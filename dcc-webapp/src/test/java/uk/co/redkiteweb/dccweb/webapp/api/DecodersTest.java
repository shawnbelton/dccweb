package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.model.DecoderFunction;
import uk.co.redkiteweb.dccweb.data.model.LinkedMacro;
import uk.co.redkiteweb.dccweb.data.model.Macro;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderFunctionRepository;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderRepository;
import uk.co.redkiteweb.dccweb.data.repositories.LinkedMacroRepository;
import uk.co.redkiteweb.dccweb.decoders.DecoderHandler;
import uk.co.redkiteweb.dccweb.decoders.DecoderHandlerFactory;
import uk.co.redkiteweb.dccweb.services.DecoderService;

import java.util.ArrayList;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 07/07/16.
 */
@RunWith(JUnit4.class)
public class DecodersTest {

    private Decoders decoders;
    private DecoderService decoderService;

    @Before
    public void setUp() {
        decoderService = mock(DecoderService.class);
        decoders = new Decoders();
        decoders.setDecoderService(decoderService);

    }

    @Test
    public void testReadDecoder() {
        when(decoderService.readDecoder()).thenReturn(new Decoder());
        assertNotNull(decoders.readDecoder());
    }

    @Test
    public void testReadDecoderAll() {
        when(decoderService.readFull()).thenReturn(new ArrayList<>());
        assertNotNull(decoders.readFull());
    }

    @Test
    public void testWriteCvs() {
        decoders.writeCVs(new ArrayList<>());
        verify(decoderService, times(1)).writeCVs(anyList());
    }

    @Test
    public void testFetchAll() {
        when(decoderService.allDecoders()).thenReturn(new ArrayList<>());
        assertNotNull(decoders.allDecoders());
    }

    @Test
    public void getById() {
        when(decoderService.getById(anyInt())).thenReturn(new Decoder());
        assertNotNull(decoders.getById(1));
    }

    @Test
    public void testAddFunction() {
        decoders.addFunction(new DecoderFunction());
        verify(decoderService, times(1)).addFunction(any(DecoderFunction.class));
    }

    @Test
    public void testDeleteFunction() {
        decoders.deleteFunction(new DecoderFunction());
        verify(decoderService, times(1)).deleteFunction(any(DecoderFunction.class));
    }

    @Test
    public void testLinkMacro() {
        decoders.linkMacro(new LinkedMacro());
        verify(decoderService, times(1)).linkMacro(any(LinkedMacro.class));
    }

    @Test
    public void testUnlinkMacro() {
        decoders.unlinkMacro(new LinkedMacro());
        verify(decoderService, times(1)).unlinkMacro(any(LinkedMacro.class));
    }
}
