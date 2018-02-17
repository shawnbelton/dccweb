package uk.co.redkiteweb.dccweb.decoders;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.DecoderSetting;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.repositories.CVRepository;
import uk.co.redkiteweb.dccweb.data.repositories.DccManufacturerRepository;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderRepository;
import uk.co.redkiteweb.dccweb.data.service.NotificationService;
import uk.co.redkiteweb.dccweb.data.store.LogStore;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.decoders.types.CVHandler;

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
public class DecoderHandlerTest {

    private DecoderHandler decoderHandler;
    private DccInterface dccInterface;
    private DecoderRepository decoderRepository;
    private LogStore logStore;
    private DefinitionReaderFactory definitionReaderFactory;
    private DefinitionReader definitionReader;
    private CVHandler cvHandler;
    private NotificationService notificationService;

    @Before
    public void setUp() throws DefinitionException {
        logStore = mock(LogStore.class);
        dccInterface = mock(DccInterface.class);
        decoderRepository = mock(DecoderRepository.class);
        final CVRepository cvRepository = mock(CVRepository.class);
        final DccManufacturerRepository dccManufacturerRepository = mock(DccManufacturerRepository.class);
        definitionReaderFactory = mock(DefinitionReaderFactory.class);
        definitionReader = mock(DefinitionReader.class);
        cvHandler = mock(CVHandler.class);
        notificationService = mock(NotificationService.class);
        decoderHandler = new DecoderHandler();
        decoderHandler.setDccInterface(dccInterface);
        decoderHandler.setDccManufacturerRepository(dccManufacturerRepository);
        decoderHandler.setDecoderRepository(decoderRepository);
        decoderHandler.setCvRepository(cvRepository);
        decoderHandler.setLogStore(logStore);
        decoderHandler.setDefinitionReaderFactory(definitionReaderFactory);
        decoderHandler.setCvHandler(cvHandler);
        decoderHandler.setNotificationService(notificationService);
        when(definitionReaderFactory.getInstance(any(CVHandler.class))).thenReturn(definitionReader);
    }

    @Test
    public void errorEnterProgramTest() {
        final MessageResponse messageResponse = mock(MessageResponse.class);
        messageResponse.setStatus(MessageResponse.MessageStatus.ERROR);
        when(dccInterface.sendMessage(any(Message.class))).thenReturn(messageResponse);
        assertNotNull(decoderHandler.readDecoderOnProgram());
    }

    @Test
    public void errorEnterProgramFullTest() {
        final MessageResponse messageResponse = mock(MessageResponse.class);
        messageResponse.setStatus(MessageResponse.MessageStatus.ERROR);
        when(dccInterface.sendMessage(any(Message.class))).thenReturn(messageResponse);
        assertTrue(decoderHandler.readFullOnProgram().isEmpty());
    }

    @Test
    public void errorEnterProgramWriteTest() throws DefinitionException {
        final MessageResponse messageResponse = mock(MessageResponse.class);
        messageResponse.setStatus(MessageResponse.MessageStatus.ERROR);
        when(dccInterface.sendMessage(any(Message.class))).thenReturn(messageResponse);
        decoderHandler.writeSettingsToDecoder(new ArrayList<>());
        verify(definitionReaderFactory, never()).getInstance(eq(cvHandler));
    }

    @Test
    public void readDecoderOKTest() throws DefinitionException {
        when(definitionReader.readValue(eq("Address Mode"))).thenReturn(1);
        readDecoderOK();
        verify(notificationService, times(1)).createNotification(anyString(), anyString());
    }

    @Test
    public void readExistingDecoderOKTest() throws DefinitionException {
        when(definitionReader.readValue(eq("Address Mode"))).thenReturn(1);
        when(decoderRepository.findByCurrentAddress(anyInt())).thenReturn(new Decoder());
        readDecoderOK();
        verify(notificationService, times(1)).createNotification(anyString(), anyString());
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
        assertFalse(decoderHandler.readFullOnProgram().isEmpty());
        verify(notificationService, times(2)).createNotification(anyString(), anyString());
    }

    @Test
    public void noDecoderFullTest() throws DefinitionException {
        final MessageResponse messageResponse = mock(MessageResponse.class);
        when(messageResponse.getStatus()).thenReturn(MessageResponse.MessageStatus.OK);
        when(dccInterface.sendMessage(any(Message.class))).thenReturn(messageResponse);
        when(definitionReaderFactory.getInstance(any(CVHandler.class))).thenThrow(mock(DecoderNotDetectedException.class));
        assertTrue(decoderHandler.readFullOnProgram().isEmpty());
    }

    @Test
    public void noDecoderWriteCVTest() throws DefinitionException {
        final MessageResponse messageResponse = mock(MessageResponse.class);
        when(messageResponse.getStatus()).thenReturn(MessageResponse.MessageStatus.OK);
        when(dccInterface.sendMessage(any(Message.class))).thenReturn(messageResponse);
        when(definitionReaderFactory.getInstance(any(CVHandler.class))).thenThrow(mock(DecoderNotDetectedException.class));
        decoderHandler.writeSettingsToDecoder(new ArrayList<>());
        verify(definitionReader, never()).readValue(anyString());
    }

    @Test
    public void definitionExceptionFullTest() throws DefinitionException {
        final MessageResponse messageResponse = mock(MessageResponse.class);
        when(messageResponse.getStatus()).thenReturn(MessageResponse.MessageStatus.OK);
        when(dccInterface.sendMessage(any(Message.class))).thenReturn(messageResponse);
        when(definitionReaderFactory.getInstance(any(CVHandler.class))).thenThrow(mock(DefinitionException.class));
        assertTrue(decoderHandler.readFullOnProgram().isEmpty());
    }

    @Test
    public void definitionExceptionWriteCVTest() throws DefinitionException {
        final MessageResponse messageResponse = mock(MessageResponse.class);
        when(messageResponse.getStatus()).thenReturn(MessageResponse.MessageStatus.OK);
        when(dccInterface.sendMessage(any(Message.class))).thenReturn(messageResponse);
        when(definitionReaderFactory.getInstance(any(CVHandler.class))).thenThrow(mock(DefinitionException.class));
        decoderHandler.writeSettingsToDecoder(new ArrayList<>());
        verify(definitionReader, never()).readValue(anyString());
    }


    private void readDecoderOK() throws DefinitionException {
        final Map<Integer, Integer> cachedCvs = new HashMap<Integer, Integer>();
        cachedCvs.put(1,2);
        final MessageResponse messageResponse = mock(MessageResponse.class);
        when(messageResponse.getStatus()).thenReturn(MessageResponse.MessageStatus.OK);
        when(dccInterface.sendMessage(any(Message.class))).thenReturn(messageResponse);
        when(cvHandler.readCV(anyInt())).thenReturn(1);
        when(definitionReader.readValue(eq("Short Address"))).thenReturn(1);
        when(definitionReader.readValue(eq("Long Address"))).thenReturn(192);
        when(cvHandler.getCVCache()).thenReturn(cachedCvs);
        when(decoderRepository.findOne(anyInt())).thenReturn(new Decoder());
        assertNotNull(decoderHandler.readDecoderOnProgram());
    }

    @Test
    public void readDecoderFailTest() {
        final MessageResponse messageResponseOK = mock(MessageResponse.class);
        when(messageResponseOK.getStatus()).thenReturn(MessageResponse.MessageStatus.OK);
        final MessageResponse messageResponseError = mock(MessageResponse.class);
        when(messageResponseError.getStatus()).thenReturn(MessageResponse.MessageStatus.ERROR);
        when(dccInterface.sendMessage(any(Message.class))).thenReturn(messageResponseOK).thenReturn(messageResponseError);
        when(decoderRepository.findOne(anyInt())).thenReturn(new Decoder());
        assertNotNull(decoderHandler.readDecoderOnProgram());
    }

    @Test
    public void readManufacturerIdNull() throws DefinitionException {
        final MessageResponse messageResponse = mock(MessageResponse.class);
        when(messageResponse.getStatus()).thenReturn(MessageResponse.MessageStatus.OK);
        when(dccInterface.sendMessage(any(Message.class))).thenReturn(messageResponse);
        when(definitionReaderFactory.getInstance(any(CVHandler.class))).thenThrow(mock(DecoderNotDetectedException.class));
        assertNotNull(decoderHandler.readDecoderOnProgram());
    }

    @Test
    public void readNotDefinedDefinition() throws DefinitionException {
        when(definitionReaderFactory.getInstance(any(CVHandler.class))).thenThrow(new DefinitionException("Error"));
        readDecoderOK();
        verify(logStore, times(1)).log(eq("error"), anyString());
    }

    @Test
    public void readShortAddressMode() throws DefinitionException {
        when(definitionReader.readValue(eq("Address Mode"))).thenReturn(0);
        readDecoderOK();
    }
}
