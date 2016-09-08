package uk.co.redkiteweb.dccweb.demo.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.readers.ReaderException;

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

    private String defaultDecoderValues;
    private BufferedReader bufferedReader = null;
    private Environment environment;

    @Autowired
    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }

    public CVValue read() {
        CVValue cvValue = null;
        try {
            cvValue = getCVValue(readLine());
        } catch (IOException ioException) {
            LOGGER.error(String.format("Unable to read %s", defaultDecoderValues), ioException);
        } catch (ReaderException readerException) {
            LOGGER.error(readerException.getMessage(), readerException);
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

    private BufferedReader getReader() throws ReaderException {
        if (bufferedReader == null) {
            defaultDecoderValues = environment.getProperty("defaultDecoderValues");
            final InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(defaultDecoderValues);
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            } else {
                throw new ReaderException(String.format("%s not found.", defaultDecoderValues));
            }
        }
        return bufferedReader;
    }

    private String readLine() throws IOException, ReaderException {
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
