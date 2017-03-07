package uk.co.redkiteweb.dccweb.nce.messages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.dccinterface.messages.UpdateFunctionsMessage;
import uk.co.redkiteweb.dccweb.nce.communication.NceData;
import uk.co.redkiteweb.dccweb.nce.communication.TalkToNCE;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 08/11/16.
 */
@RunWith(JUnit4.class)
public class NceUpdateFunctionsMessageTest {

    private NceUpdateFunctionsMessage nceUpdateFunctionsMessage;
    private TalkToNCE talkToNCE;
    private NceData nceData;

    @Before
    public void setup() throws ConnectionException {
        nceData = mock(NceData.class);
        when(nceData.size()).thenReturn(1);
        when(nceData.readData()).thenReturn(33);
        talkToNCE = mock(TalkToNCE.class);
        when(talkToNCE.sendData(any(NceData.class))).thenReturn(nceData);
        nceUpdateFunctionsMessage = new NceUpdateFunctionsMessage();
        nceUpdateFunctionsMessage.setTalkToNCE(talkToNCE);
    }

    @Test
    public void testAddressLow() throws ConnectionException {
        final UpdateFunctionsMessage updateFunctionsMessage = getUpdateFunctionsMessage(false);
        testProcess(updateFunctionsMessage);
    }

    @Test
    public void testAddressHigh() throws ConnectionException {
        final UpdateFunctionsMessage updateFunctionsMessage = getUpdateFunctionsMessage(true);
        testProcess(updateFunctionsMessage);
    }

    @Test
    public void testFunctionZeroOn() throws ConnectionException {
        final UpdateFunctionsMessage updateFunctionsMessage = getUpdateFunctionsMessage(true);
        updateFunctionsMessage.addFunction(0,true);
        testProcess(updateFunctionsMessage);
    }

    @Test
    public void testHighFunctionOn() throws ConnectionException {
        final UpdateFunctionsMessage updateFunctionsMessage = getUpdateFunctionsMessage(true);
        updateFunctionsMessage.addFunction(15,true);
        testProcess(updateFunctionsMessage);
    }

    @Test
    public void testLowFunctionOn() throws ConnectionException {
        final UpdateFunctionsMessage updateFunctionsMessage = getUpdateFunctionsMessage(true);
        updateFunctionsMessage.addFunction(7,true);
        testProcess(updateFunctionsMessage);
    }

    @Test
    public void testProcessFail() throws ConnectionException {
        when(nceData.readData()).thenReturn(31);
        final UpdateFunctionsMessage updateFunctionsMessage = getUpdateFunctionsMessage(false);
        final MessageResponse messageResponse = nceUpdateFunctionsMessage.process(updateFunctionsMessage);
        assertEquals(MessageResponse.MessageStatus.ERROR, messageResponse.getStatus());
        verify(talkToNCE, times(5)).sendData(any(NceData.class));
    }

    private UpdateFunctionsMessage getUpdateFunctionsMessage(boolean addressMode) {
        final UpdateFunctionsMessage updateFunctionsMessage = new UpdateFunctionsMessage();
        updateFunctionsMessage.setAddress(12);
        updateFunctionsMessage.setAddressMode(addressMode);
        return updateFunctionsMessage;
    }

    private void testProcess(UpdateFunctionsMessage updateFunctionsMessage) throws ConnectionException {
        final MessageResponse messageResponse = nceUpdateFunctionsMessage.process(updateFunctionsMessage);
        assertEquals(MessageResponse.MessageStatus.OK, messageResponse.getStatus());
        verify(talkToNCE, times(5)).sendData(any(NceData.class));
    }

}
