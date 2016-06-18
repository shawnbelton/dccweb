package uk.co.redkiteweb.dccweb.nce.factories;

import gnu.io.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;

/**
 * Created by shawn on 17/06/16.
 */
@Component
public class PortFactory {

    private static final Logger LOGGER = Logger.getLogger(PortFactory.class);

    private String connectionName;
    private SerialPort serialPort;

    public PortFactory() {
        serialPort = null;
    }

    @Value("${nce.port}")
    public void setConnectionName(final String connectionName) {
        this.connectionName = connectionName;
    }

    public SerialPort getSerialPort() throws ConnectionException {
        if (serialPort == null) {
            buildSerialPort();
        }
        return serialPort;
    }

    public void close() {
        if (serialPort != null) {
            LOGGER.info("Closing connection.");
            serialPort.close();
        }
    }

    private void buildSerialPort() throws ConnectionException {
        final CommPort commPort = connect();
        if (commPort instanceof SerialPort) {
            serialPort = (SerialPort) commPort;
            try {
                serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                serialPort.enableReceiveTimeout(100);
            } catch (UnsupportedCommOperationException exception) {
                throw new ConnectionException("Unable to set serial port parameters", exception);
            }
        }
    }

    private CommPort connect() throws ConnectionException {
        CommPort commPort;
        try {
            final CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(connectionName);
            if (portIdentifier.isCurrentlyOwned()) {
                throw new ConnectionException(String.format("Connection already owned by %s", portIdentifier.getCurrentOwner()));
            } else {
                commPort = portIdentifier.open(this.getClass().getName(), 9600);
            }
        } catch (NoSuchPortException exception) {
            throw new ConnectionException(String.format("Port name %s is incorrect.", connectionName), exception);
        } catch (PortInUseException exception) {
            throw new ConnectionException("Connection is in use", exception);
        }
        return commPort;
    }

}