package uk.co.redkiteweb.dccweb.readers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.DecoderSetting;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.repositories.CVRepository;
import uk.co.redkiteweb.dccweb.data.repositories.DccManufacturerRepository;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderRepository;
import uk.co.redkiteweb.dccweb.data.store.LogStore;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.decoders.DecoderNotDetectedException;
import uk.co.redkiteweb.dccweb.decoders.DefinitionException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 07/07/16.
 */
@RunWith(JUnit4.class)
public class DecoderReaderTest {

    private DecoderReader decoderReader;
    private DccInterface dccInterface;
    private DecoderRepository decoderRepository;
    private LogStore logStore;
    private DefinitionReaderFactory definitionReaderFactory;
    private DefinitionReader definitionReader;
    private CVReader cvReader;

    @Before
    public void setUp() throws DefinitionException {
        logStore = mock(LogStore.class);
        dccInterface = mock(DccInterface.class);
        decoderRepository = mock(DecoderRepository.class);
        final CVRepository cvRepository = mock(CVRepository.class);
        final DccManufacturerRepository dccManufacturerRepository = mock(DccManufacturerRepository.class);
        definitionReaderFactory = mock(DefinitionReaderFactory.class);
        definitionReader = mock(DefinitionReader.class);
        cvReader = mock(CVReader.class);
        decoderReader = new DecoderReader();
        decoderReader.setDccInterface(dccInterface);
        decoderReader.setDccManufacturerRepository(dccManufacturerRepository);
        decoderReader.setDecoderRepository(decoderRepository);
        decoderReader.setCvRepository(cvRepository);
        decoderReader.setLogStore(logStore);
        decoderReader.setDefinitionReaderFactory(definitionReaderFactory);
        decoderReader.setCvReader(cvReader);
        when(definitionReaderFactory.getInstance(any(CVReader.class))).thenReturn(definitionReader);
    }

    @Test
    public void errorEnterProgramTest() {
        final MessageResponse messageResponse = mock(MessageResponse.class);
        messageResponse.setStatus(MessageResponse.MessageStatus.ERROR);
        when(dccInterface.sendMessage(any(Message.class))).thenReturn(messageResponse);
        assertNotNull(decoderReader.readDecoderOnProgram());
    }

    @Test
    public void errorEnterProgramFullTest() {
        final MessageResponse messageResponse = mock(MessageResponse.class);
        messageResponse.setStatus(MessageResponse.MessageStatus.ERROR);
        when(dccInterface.sendMessage(any(Message.class))).thenReturn(messageResponse);
        assertTrue(decoderReader.readFullOnProgram(1).isEmpty());
    }

    @Test
    public void readDecoderOKTest() throws DefinitionException {
        when(definitionReader.readValue(eq("Address Mode"))).thenReturn(1);
        readDecoderOK();
    }

    @Test
    public void readExistingDecoderOKTest() throws DefinitionException {
        when(definitionReader.readValue(eq("Address Mode"))).thenReturn(1);
        when(decoderRepository.findByCurrentAddress(anyInt())).thenReturn(new Decoder());
        readDecoderOK();
    }

    @Test
    public void readFullOKTest() throws DefinitionException {
        final MessageResponse messageResponse = mock(MessageResponse.class);
        when(messageResponse.getStatus()).thenReturn(MessageResponse.MessageStatus.OK);
        when(dccInterface.sendMessage(any(Message.class))).thenReturn(messageResponse);
        when(decoderRepository.findOne(anyInt())).thenReturn(mock(Decoder.class));
        final List<DecoderSetting> decoderSettings = new ArrayList<>();
        decoderSettings.add(mock(DecoderSetting.class));
        when(definitionReader.readAllValues()).thenReturn(decoderSettings);
        assertFalse(decoderReader.readFullOnProgram(1).isEmpty());
    }

    @Test
    public void noDecoderFullTest() throws DefinitionException {
        final MessageResponse messageResponse = mock(MessageResponse.class);
        when(messageResponse.getStatus()).thenReturn(MessageResponse.MessageStatus.OK);
        when(dccInterface.sendMessage(any(Message.class))).thenReturn(messageResponse);
        when(definitionReaderFactory.getInstance(any(CVReader.class))).thenThrow(mock(DecoderNotDetectedException.class));
        assertTrue(decoderReader.readFullOnProgram(1).isEmpty());
    }

    @Test
    public void definitionExceptionFullTest() throws DefinitionException {
        final MessageResponse messageResponse = mock(MessageResponse.class);
        when(messageResponse.getStatus()).thenReturn(MessageResponse.MessageStatus.OK);
        when(dccInterface.sendMessage(any(Message.class))).thenReturn(messageResponse);
        when(definitionReaderFactory.getInstance(any(CVReader.class))).thenThrow(mock(DefinitionException.class));
        assertTrue(decoderReader.readFullOnProgram(1).isEmpty());
    }


    private void readDecoderOK() throws DefinitionException {
        final Map<Integer, Integer> cachedCvs = new HashMap<Integer, Integer>();
        cachedCvs.put(1,2);
        final MessageResponse messageResponse = mock(MessageResponse.class);
        when(messageResponse.getStatus()).thenReturn(MessageResponse.MessageStatus.OK);
        when(dccInterface.sendMessage(any(Message.class))).thenReturn(messageResponse);
        when(cvReader.readCV(anyInt())).thenReturn(1);
        when(definitionReader.readValue(eq("Short Address"))).thenReturn(1);
        when(definitionReader.readValue(eq("Long Address"))).thenReturn(192);
        when(cvReader.getCVCache()).thenReturn(cachedCvs);
        when(decoderRepository.findOne(anyInt())).thenReturn(new Decoder());
        assertNotNull(decoderReader.readDecoderOnProgram());
    }

    @Test
    public void readDecoderFailTest() {
        final MessageResponse messageResponseOK = mock(MessageResponse.class);
        when(messageResponseOK.getStatus()).thenReturn(MessageResponse.MessageStatus.OK);
        final MessageResponse messageResponseError = mock(MessageResponse.class);
        when(messageResponseError.getStatus()).thenReturn(MessageResponse.MessageStatus.ERROR);
        when(dccInterface.sendMessage(any(Message.class))).thenReturn(messageResponseOK).thenReturn(messageResponseError);
        when(decoderRepository.findOne(anyInt())).thenReturn(new Decoder());
        assertNotNull(decoderReader.readDecoderOnProgram());
    }

    @Test
    public void readManufacturerIdNull() throws DefinitionException {
        final MessageResponse messageResponse = mock(MessageResponse.class);
        when(messageResponse.getStatus()).thenReturn(MessageResponse.MessageStatus.OK);
        when(dccInterface.sendMessage(any(Message.class))).thenReturn(messageResponse);
        when(definitionReaderFactory.getInstance(any(CVReader.class))).thenThrow(mock(DecoderNotDetectedException.class));
        assertNotNull(decoderReader.readDecoderOnProgram());
    }

    @Test
    public void readNotDefinedDefinition() throws DefinitionException {
        when(definitionReaderFactory.getInstance(any(CVReader.class))).thenThrow(new DefinitionException("Error"));
        readDecoderOK();
        verify(logStore, times(1)).log(eq("error"), anyString());
    }

    @Test
    public void readShortAddressMode() throws DefinitionException {
        when(definitionReader.readValue(eq("Address Mode"))).thenReturn(0);
        readDecoderOK();
    }
}
