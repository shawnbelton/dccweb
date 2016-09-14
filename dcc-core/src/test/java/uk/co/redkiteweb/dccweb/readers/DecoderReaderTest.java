package uk.co.redkiteweb.dccweb.readers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.repositories.CVRepository;
import uk.co.redkiteweb.dccweb.data.repositories.DccManufacturerRepository;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderRepository;
import uk.co.redkiteweb.dccweb.data.store.LogStore;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 07/07/16.
 */
@RunWith(JUnit4.class)
public class DecoderReaderTest {

    private DecoderReader decoderReader;
    private DccInterface dccInterface;
    private DccManufacturerRepository dccManufacturerRepository;
    private DecoderRepository decoderRepository;
    private CVRepository cvRepository;
    private LogStore logStore;

    @Before
    public void setUp() {
        logStore = mock(LogStore.class);
        dccInterface = mock(DccInterface.class);
        decoderRepository = mock(DecoderRepository.class);
        cvRepository = mock(CVRepository.class);
        dccManufacturerRepository = mock(DccManufacturerRepository.class);
        decoderReader = new DecoderReader();
        decoderReader.setDccInterface(dccInterface);
        decoderReader.setDccManufacturerRepository(dccManufacturerRepository);
        decoderReader.setDecoderRepository(decoderRepository);
        decoderReader.setCvRepository(cvRepository);
        decoderReader.setLogStore(logStore);
    }

    @Test
    public void errorEnterProgramTest() {
        final MessageResponse messageResponse = mock(MessageResponse.class);
        messageResponse.setStatus(MessageResponse.MessageStatus.ERROR);
        when(dccInterface.sendMessage(any(Message.class))).thenReturn(messageResponse);
        assertNotNull(decoderReader.readDecoderOnProgram());
    }

    @Test
    public void readDecoderOKTest() {
        readDecoderOK();
    }

    @Test
    public void readExistingDecoderOKTest() {
        when(decoderRepository.findByShortAddressAndLongAddress(anyInt(), anyInt())).thenReturn(new Decoder());
        readDecoderOK();
    }

    private void readDecoderOK() {
        final MessageResponse messageResponse = mock(MessageResponse.class);
        when(messageResponse.getStatus()).thenReturn(MessageResponse.MessageStatus.OK);
        when(dccInterface.sendMessage(any(Message.class))).thenReturn(messageResponse);
        when(messageResponse.get(eq("CVData"))).thenReturn(new Integer(1));
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

}
