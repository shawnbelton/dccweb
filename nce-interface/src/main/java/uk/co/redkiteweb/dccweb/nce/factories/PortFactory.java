package uk.co.redkiteweb.dccweb.nce.factories;

import gnu.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;

/**
 * Created by shawn on 17/06/16.
 */
@Component
public class PortFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(PortFactory.class);
    private static final int BAUD_RATE = 9600;
    private static final int RECEIVE_TIMEOUT = 10;

    private NcePortIdentifier ncePortIdentifier;
    private SerialPort serialPort;

    public PortFactory() {
        serialPort = null;
    }

    @Autowired
    public void setNcePortIdentifier(final NcePortIdentifier ncePortIdentifier) {
        this.ncePortIdentifier = ncePortIdentifier;
    }

    public SerialPort getSerialPort() throws ConnectionException {
        if (serialPort == null) {
            buildSerialPort(connect());
        }
        return serialPort;
    }

    public void close() {
        if (serialPort != null) {
            LOGGER.info("Closing connection.");
            serialPort.close();
            serialPort = null;
        }
    }

    private void buildSerialPort(final CommPort commPort) throws ConnectionException {
        if (commPort instanceof SerialPort) {
            configurePort((SerialPort) commPort);
        }
    }

    private void configurePort(final SerialPort port) throws ConnectionException {
        try {
            port.setSerialPortParams(BAUD_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            port.enableReceiveTimeout(RECEIVE_TIMEOUT);
            serialPort = port;
        } catch (UnsupportedCommOperationException exception) {
            throw new ConnectionException("Unable to set serial port parameters", exception);
        }
    }

    private CommPort connect() throws ConnectionException {
        try {
            return getPortIdentifier().open(this.getClass().getName(), BAUD_RATE);
        } catch (PortInUseException exception) {
            throw new ConnectionException("Connection is in use", exception);
        }
    }

    private CommPortIdentifier getPortIdentifier() throws ConnectionException {
        try {
            final CommPortIdentifier portIdentifier = ncePortIdentifier.getInstance();
            if (portIdentifier.isCurrentlyOwned()) {
                throw new ConnectionException(String.format("Connection already owned by %s", portIdentifier.getCurrentOwner()));
            }
            return portIdentifier;
        } catch (NoSuchPortException exception) {
            throw new ConnectionException(exception.getMessage(), exception);
        }
    }

}
