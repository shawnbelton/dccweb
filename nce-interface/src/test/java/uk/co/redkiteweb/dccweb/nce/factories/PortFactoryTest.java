package uk.co.redkiteweb.dccweb.nce.factories;

import gnu.io.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;

import javax.sound.sampled.Port;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 19/06/16.
 */
@RunWith(JUnit4.class)
public class PortFactoryTest {

    private PortFactory portFactory;
    private NcePortIdentifier ncePortIdentifier;
    private CommPortIdentifier commPortIdentifier;
    private SerialPort serialPort;

    @Before
    public void setUp() {
        ncePortIdentifier = mock(NcePortIdentifier.class);
        commPortIdentifier = mock(CommPortIdentifier.class);
        serialPort = mock(SerialPort.class);
        portFactory = new PortFactory();
        portFactory.setNcePortIdentifier(ncePortIdentifier);
    }

    @Test
    public void closeTest() {
        portFactory.close();
    }

    @Test
    public void noSuchPortTest() throws NoSuchPortException {
        try {
            doThrow(mock(NoSuchPortException.class)).when(ncePortIdentifier).getInstance();
            portFactory.getSerialPort();
            fail();
        } catch (ConnectionException exception) {
            assertNull(exception.getMessage());
        }
    }

    @Test
    public void allReadyOwned() throws NoSuchPortException {
        try {
            when(ncePortIdentifier.getInstance()).thenReturn(commPortIdentifier);
            when(commPortIdentifier.isCurrentlyOwned()).thenReturn(true);
            when(commPortIdentifier.getCurrentOwner()).thenReturn("Owner");
            portFactory.getSerialPort();
            fail();
        } catch (ConnectionException exception) {
            assertEquals("Connection already owned by Owner", exception.getMessage());
        }
    }

    @Test
    public void connectionInUseTest() throws NoSuchPortException, PortInUseException {
        try {
            when(ncePortIdentifier.getInstance()).thenReturn(commPortIdentifier);
            when(commPortIdentifier.isCurrentlyOwned()).thenReturn(false);
            doThrow(mock(PortInUseException.class)).when(commPortIdentifier).open(anyString(), anyInt());
            portFactory.getSerialPort();
            fail();
        } catch (ConnectionException exception) {
            assertEquals("Connection is in use", exception.getMessage());
        }
    }

    @Test
    public void unsupportedCommOperationTest() throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException {
        try {
            when(ncePortIdentifier.getInstance()).thenReturn(commPortIdentifier);
            when(commPortIdentifier.isCurrentlyOwned()).thenReturn(false);
            when(commPortIdentifier.open(anyString(), anyInt())).thenReturn(serialPort);
            doThrow(mock(UnsupportedCommOperationException.class)).when(serialPort).enableReceiveTimeout(anyInt());
            portFactory.getSerialPort();
            fail();
        } catch (ConnectionException exception) {
            assertEquals("Unable to set serial port parameters", exception.getMessage());
        }
    }

    @Test
    public void notASerialPort() throws NoSuchPortException, PortInUseException,
            ConnectionException, UnsupportedCommOperationException {
        final CommPort commPort = mock(CommPort.class);
        when(ncePortIdentifier.getInstance()).thenReturn(commPortIdentifier);
        when(commPortIdentifier.isCurrentlyOwned()).thenReturn(false);
        when(commPortIdentifier.open(anyString(),anyInt())).thenReturn(commPort);
        portFactory.getSerialPort();
        verify(serialPort, never()).enableReceiveTimeout(anyInt());
    }

    @Test
    public void getSerialPortTest() throws NoSuchPortException, PortInUseException,
            ConnectionException, UnsupportedCommOperationException {
        when(ncePortIdentifier.getInstance()).thenReturn(commPortIdentifier);
        when(commPortIdentifier.isCurrentlyOwned()).thenReturn(false);
        when(commPortIdentifier.open(anyString(),anyInt())).thenReturn(serialPort);
        portFactory.getSerialPort();
        verify(serialPort, times(1)).enableReceiveTimeout(anyInt());
    }

    @Test
    public void getStoredSerialPortTest() throws NoSuchPortException, PortInUseException,
            ConnectionException, UnsupportedCommOperationException {
        when(ncePortIdentifier.getInstance()).thenReturn(commPortIdentifier);
        when(commPortIdentifier.isCurrentlyOwned()).thenReturn(false);
        when(commPortIdentifier.open(anyString(),anyInt())).thenReturn(serialPort);
        portFactory.getSerialPort();
        portFactory.getSerialPort();
        verify(serialPort, times(1)).enableReceiveTimeout(anyInt());
    }

    @Test
    public void closeOpenPortTest() throws NoSuchPortException, PortInUseException, ConnectionException {
        when(ncePortIdentifier.getInstance()).thenReturn(commPortIdentifier);
        when(commPortIdentifier.isCurrentlyOwned()).thenReturn(false);
        when(commPortIdentifier.open(anyString(),anyInt())).thenReturn(serialPort);
        portFactory.getSerialPort();
        portFactory.close();
        verify(serialPort, times(1)).close();
    }
}
