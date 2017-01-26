package uk.co.redkiteweb.dccweb.demo.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by shawn on 26/07/16.
 */
@Component
public class DecoderDefaultReader {

    private static final Logger LOGGER = LogManager.getLogger(DecoderDefaultReader.class);
    private static final String DEFAULT_DECODER_VALUES = "decoderDefaults.csv";

    private BufferedReader bufferedReader = null;

    public CVValue read() {
        CVValue cvValue = null;
        try {
            cvValue = getCVValue(readLine());
        } catch (IOException ioException) {
            LOGGER.error(String.format("Unable to read %s", DEFAULT_DECODER_VALUES), ioException);
        }
        return cvValue;
    }

    private static CVValue getCVValue(final String readLine) {
        CVValue cvValue = null;
        if (readLine != null) {
            final String[] parts = readLine.split(",");
            if (parts.length == 2) {
                cvValue = getCVValue(parts);
            }
        }
        return cvValue;
    }

    private static CVValue getCVValue(final String[] parts) {
        final CVValue cvValue = new CVValue();
        cvValue.setNumber(Integer.parseInt(parts[0]));
        cvValue.setValue(Integer.parseInt(parts[1]));
        return cvValue;
    }

    private BufferedReader getReader() throws IOException {
        if (bufferedReader == null) {
            final InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(DEFAULT_DECODER_VALUES);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        }
        return bufferedReader;
    }

    private String readLine() throws IOException {
        final String readLine = getReader().readLine();
        if (readLine == null) {
            closeBufferedReader();
        }
        return readLine;
    }

    private void closeBufferedReader() throws IOException {
        bufferedReader.close();
    }
}
