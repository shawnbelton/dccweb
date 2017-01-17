package uk.co.redkiteweb.dccweb.nce.communication;

import gnu.io.SerialPort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;
import uk.co.redkiteweb.dccweb.nce.factories.PortFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * Created by shawn on 17/06/16.
 */
@Component
public class TalkToNCE {

    private static final Logger LOGGER = LogManager.getLogger(TalkToNCE.class);

    private PortFactory portFactory;

    @Autowired
    public void setPortFactory(final PortFactory portFactory) {
        this.portFactory = portFactory;
    }

    public void shutdown() {
        portFactory.close();
    }

    public NceData sendData(final NceData inData) throws ConnectionException {
        NceData outData;
        final long startTicker = new Date().getTime();
        final SerialPort serialPort = portFactory.getSerialPort();
        try {
            synchronized (this) {
                writeData(serialPort, inData);
                outData = readData(serialPort, inData);
            }
        } catch (IOException exception) {
            portFactory.close();
            throw new ConnectionException("Send Data failed", exception);
        }
        final long endTicker = new Date().getTime();
        LOGGER.info(String.format("NCE Communication time: %d data items read: %d", endTicker-startTicker, outData.size()));
        return outData;
    }

    private static void writeData(final SerialPort serialPort, final NceData inData) throws IOException {
        final OutputStream outputStream = serialPort.getOutputStream();
        Integer outputData = inData.readData();
        while (outputData != null) {
            LOGGER.debug(String.format("Sending: %d",outputData));
            outputStream.write(outputData);
            outputData = inData.readData();
        }
        outputStream.flush();
        outputStream.close();
    }

    private static NceData readData(final SerialPort serialPort, final NceData inData) throws IOException {
        final NceData outData = new NceData();
        final InputStream inputStream = serialPort.getInputStream();
        int inputData = inputStream.read();
        do {
            if (inputData >= 0) {
                LOGGER.debug(String.format("Receiving: %d", inputData));
                outData.addData(inputData);
            }
            inputData = inputStream.read();
        } while (inData.getExpectedValues()!=outData.size());
        inputStream.close();
        return outData;
    }
}
