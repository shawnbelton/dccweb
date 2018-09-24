package uk.co.redkiteweb.dccweb.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.model.DecoderFunction;
import uk.co.redkiteweb.dccweb.data.model.LinkedMacro;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderFunctionRepository;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderRepository;
import uk.co.redkiteweb.dccweb.data.repositories.LinkedMacroRepository;
import uk.co.redkiteweb.dccweb.decoders.DecoderHandler;
import uk.co.redkiteweb.dccweb.decoders.DecoderHandlerFactory;
import uk.co.redkiteweb.dccweb.store.LogStore;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 07/07/16.
 */
@RunWith(JUnit4.class)
public class DecoderServiceTest {

    private DecoderService decoderService;
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
        decoderService = new DecoderService();
        decoderService.setDecoderHandlerFactory(decoderHandlerFactory);
        decoderService.setDecoderRepository(decoderRepository);
        decoderService.setDecoderFunctionRepository(decoderFunctionRepository);
        decoderService.setLinkedMacroRepository(linkedMacroRepository);
        decoderService.setLogStore(logStore);
    }

    @Test
    public void testReadDecoder() {
        when(decoderHandler.readDecoderOnProgram()).thenReturn(new Decoder());
        assertNotNull(decoderService.readDecoder());
    }

    @Test
    public void testReadDecoderAll() {
        when(decoderHandler.readFullOnProgram()).thenReturn(new ArrayList<>());
        assertNotNull(decoderService.readFull());
    }

    @Test
    public void testWriteCvs() {
        decoderService.writeCVs(new ArrayList<>());
        verify(decoderHandler, times(1)).writeSettingsToDecoder(anyList());
    }

    @Test
    public void testFetchAll() {
        when(decoderRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(decoderService.allDecoders());
    }

    @Test
    public void getById() {
        when(decoderRepository.findById(anyInt())).thenReturn(Optional.of(new Decoder()));
        assertNotNull(decoderService.getById(1));
    }

    @Test
    public void testAddFunction() {
        final DecoderFunction decoderFunction = new DecoderFunction();
        decoderFunction.setDecoderId(1);
        decoderFunction.setNumber(1);
        decoderFunction.setName("Sound");
        when(decoderRepository.findById(anyInt())).thenReturn(Optional.of(new Decoder()));
        assertNotNull(decoderService.addFunction(decoderFunction));
        verify(decoderFunctionRepository, times(1)).save(any(DecoderFunction.class));
    }

    @Test
    public void testDeleteFunction() {
        final DecoderFunction decoderFunction = new DecoderFunction();
        decoderFunction.setDecoderId(1);
        decoderFunction.setNumber(1);
        decoderFunction.setName("Sound");
        when(decoderRepository.findById(anyInt())).thenReturn(Optional.of(new Decoder()));
        assertNotNull(decoderService.deleteFunction(decoderFunction));
        verify(decoderFunctionRepository, times(1)).delete(any(DecoderFunction.class));
    }

    @Test
    public void testLinkMacro() {
        final LinkedMacro linkedMacro = new LinkedMacro();
        linkedMacro.setMacroId(1);
        when(decoderRepository.findById(anyInt())).thenReturn(Optional.of(new Decoder()));
        assertNotNull(decoderService.linkMacro(linkedMacro));
        verify(linkedMacroRepository, times(1)).save(any(LinkedMacro.class));
    }

    @Test
    public void testUnlinkMacro() {
        final LinkedMacro linkedMacro = new LinkedMacro();
        linkedMacro.setMacroId(1);
        when(decoderRepository.findById(anyInt())).thenReturn(Optional.of(new Decoder()));
        assertNotNull(decoderService.unlinkMacro(linkedMacro));
        verify(linkedMacroRepository, times(1)).delete(any(LinkedMacro.class));
    }
}
