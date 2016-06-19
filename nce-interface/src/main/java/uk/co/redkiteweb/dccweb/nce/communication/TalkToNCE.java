package uk.co.redkiteweb.dccweb.nce.communication;

import gnu.io.SerialPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;
import uk.co.redkiteweb.dccweb.nce.factories.PortFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by shawn on 17/06/16.
 */
@Component
public class TalkToNCE {

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
        final SerialPort serialPort = portFactory.getSerialPort();
        try {
            writeData(serialPort, inData);
            outData = readData(serialPort);
        } catch (IOException exception) {
            portFactory.close();
            throw new ConnectionException("Send Data failed", exception);
        }
        return outData;
    }

    private static void writeData(final SerialPort serialPort, final NceData inData) throws IOException {
        final OutputStream outputStream = serialPort.getOutputStream();
        Integer outputData = inData.readData();
        while (outputData != null) {
            outputStream.write(outputData);
            outputData = inData.readData();
        }
        outputStream.flush();
        outputStream.close();
    }

    private static NceData readData(final SerialPort serialPort) throws IOException {
        final NceData outData = new NceData();
        final InputStream inputStream = serialPort.getInputStream();
        int inputData = inputStream.read();
        while (inputData >= 0) {
            outData.addData(inputData);
            inputData = inputStream.read();
        }
        inputStream.close();
        return outData;
    }
}
