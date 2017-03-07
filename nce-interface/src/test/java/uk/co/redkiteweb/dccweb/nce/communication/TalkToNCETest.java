package uk.co.redkiteweb.dccweb.nce.communication;

import gnu.io.SerialPort;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.omg.CORBA.portable.OutputStream;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;
import uk.co.redkiteweb.dccweb.nce.factories.PortFactory;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 18/06/16.
 */
@RunWith(JUnit4.class)
public class TalkToNCETest {

    private TalkToNCE talkToNce;
    private PortFactory portFactory;
    private SerialPort serialPort;
    private InputStream inputStream;
    private OutputStream outputStream;

    @Before
    public void setUp() throws ConnectionException, IOException {
        portFactory = mock(PortFactory.class);
        serialPort = mock(SerialPort.class);
        inputStream = mock(InputStream.class);
        outputStream = mock(OutputStream.class);
        talkToNce = new TalkToNCE();
        talkToNce.setPortFactory(portFactory);
        when(portFactory.getSerialPort()).thenReturn(serialPort);
        when(serialPort.getInputStream()).thenReturn(inputStream);
        when(serialPort.getOutputStream()).thenReturn(outputStream);
    }

    @Test
    public void shutdownTest() {
        talkToNce.shutdown();
        verify(portFactory, times(1)).close();
    }

    @Test
    public void sendDataTest() throws ConnectionException, IOException {
        final NceData nceData = new NceData();
        nceData.addData(80);
        when(inputStream.read()).thenReturn(33).thenReturn(-1);
        final NceData outData = talkToNce.sendData(nceData);
        verify(outputStream, times(1)).write(eq(80));
        assertEquals(33, (int)outData.readData());
    }

    @Test
    public void setDataTimeoutTest() throws ConnectionException, IOException {
        final NceData nceData = new NceData();
        nceData.addData(80);
        when(inputStream.read()).thenReturn(-1);
        final NceData outData = talkToNce.sendData(nceData);
        verify(outputStream, times(1)).write(eq(80));
        assertTrue(outData.isEmpty());
    }

    @Test
    public void errorSendingData() throws IOException {
        try {
            final NceData nceData = new NceData();
            doThrow(mock(IOException.class)).when(serialPort).getOutputStream();
            talkToNce.sendData(nceData);
            fail();
        } catch (ConnectionException exception) {
            assertEquals("Send Data failed", exception.getMessage());
        }
    }

}
