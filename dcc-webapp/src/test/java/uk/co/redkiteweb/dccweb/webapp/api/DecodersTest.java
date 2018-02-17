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
import uk.co.redkiteweb.dccweb.data.store.LogStore;
import uk.co.redkiteweb.dccweb.decoders.DecoderHandler;
import uk.co.redkiteweb.dccweb.decoders.DecoderHandlerFactory;

import java.util.ArrayList;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 07/07/16.
 */
@RunWith(JUnit4.class)
public class DecodersTest {

    private Decoders decoders;
    private DecoderHandler decoderHandler;
    private DecoderRepository decoderRepository;
    private DecoderFunctionRepository decoderFunctionRepository;
    private LinkedMacroRepository linkedMacroRepository;

    @Before
    public void setUp() {
        decoderHandler = mock(DecoderHandler.class);
        final DecoderHandlerFactory decoderHandlerFactory = mock(DecoderHandlerFactory.class);
        when(decoderHandlerFactory.createInstance()).thenReturn(decoderHandler);
        decoderRepository = mock(DecoderRepository.class);
        decoderFunctionRepository = mock(DecoderFunctionRepository.class);
        final LogStore logStore = mock(LogStore.class);
        linkedMacroRepository = mock(LinkedMacroRepository.class);
        decoders = new Decoders();
        decoders.setDecoderHandlerFactory(decoderHandlerFactory);
        decoders.setDecoderRepository(decoderRepository);
        decoders.setDecoderFunctionRepository(decoderFunctionRepository);
        decoders.setLinkedMacroRepository(linkedMacroRepository);
        decoders.setLogStore(logStore);
    }

    @Test
    public void testReadDecoder() {
        when(decoderHandler.readDecoderOnProgram()).thenReturn(new Decoder());
        assertNotNull(decoders.readDecoder());
    }

    @Test
    public void testReadDecoderAll() {
        when(decoderHandler.readFullOnProgram()).thenReturn(new ArrayList<>());
        assertNotNull(decoders.readFull());
    }

    @Test
    public void testWriteCvs() {
        decoders.writeCVs(new ArrayList<>());
        verify(decoderHandler, times(1)).writeSettingsToDecoder(anyList());
    }

    @Test
    public void testFetchAll() {
        when(decoderRepository.findAll()).thenReturn(new ArrayList<>());
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

    @Test
    public void testDeleteFunction() {
        final DecoderFunction decoderFunction = new DecoderFunction();
        decoderFunction.setDecoderId(1);
        decoderFunction.setNumber(1);
        decoderFunction.setName("Sound");
        when(decoderRepository.findOne(anyInt())).thenReturn(new Decoder());
        assertNotNull(decoders.deleteFunction(decoderFunction));
        verify(decoderFunctionRepository, times(1)).delete(any(DecoderFunction.class));
    }

    @Test
    public void testLinkMacro() {
        final LinkedMacro linkedMacro = new LinkedMacro();
        final Macro macro = new Macro();
        macro.setName("Test Macro");
        linkedMacro.setMacro(macro);
        when(decoderRepository.findOne(anyInt())).thenReturn(new Decoder());
        assertNotNull(decoders.linkMacro(linkedMacro));
        verify(linkedMacroRepository, times(1)).save(any(LinkedMacro.class));
    }

    @Test
    public void testUnlinkMacro() {
        final LinkedMacro linkedMacro = new LinkedMacro();
        final Macro macro = new Macro();
        macro.setName("Test Macro");
        linkedMacro.setMacro(macro);
        when(decoderRepository.findOne(anyInt())).thenReturn(new Decoder());
        assertNotNull(decoders.unlinkMacro(linkedMacro));
        verify(linkedMacroRepository, times(1)).delete(any(LinkedMacro.class));
    }
}
