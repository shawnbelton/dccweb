package uk.co.redkiteweb.dccweb.data.readers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.DccManufacturer;

import java.io.*;

/**
 * Created by shawn on 30/06/16.
 */
@Component
public class DccManufacturerReader implements Reader<DccManufacturer> {

    private static final Logger LOGGER = Logger.getLogger(DccManufacturerReader.class);

    private String dccManufacturersFile;
    private BufferedReader bufferedReader = null;

    @Value("${dccManufacturerFile}")
    public void setDccManufacturersFile(final String dccManufacturersFile) {
        this.dccManufacturersFile = dccManufacturersFile;
    }

    @Override
    public DccManufacturer read() {
        DccManufacturer dccManufacturer = null;
        try {
            dccManufacturer = getDccManufacturer(readLine());
        } catch (IOException ioException) {
            LOGGER.error(String.format("Unable to read %s", dccManufacturersFile), ioException);
        } catch (ReaderException readerException) {
            LOGGER.error(readerException.getMessage(), readerException);
        }
        return dccManufacturer;
    }

    private DccManufacturer getDccManufacturer(String readLine) {
        DccManufacturer dccManufacturer = null;
        if (readLine != null) {
            final String[] parts = readLine.split(",");
            if (parts.length == 3) {
                dccManufacturer = getDccManufacturer(parts);
            }
        }
        return dccManufacturer;
    }

    private static DccManufacturer getDccManufacturer(final String[] parts) {
        final DccManufacturer dccManufacturer = new DccManufacturer();
        dccManufacturer.setManufacturerId(Integer.parseInt(parts[0]));
        dccManufacturer.setManufacturer(parts[1]);
        dccManufacturer.setCountry(parts[2]);
        return dccManufacturer;
    }

    private BufferedReader getReader() throws ReaderException {
        if (bufferedReader == null) {
            final InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(dccManufacturersFile);
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            } else {
                throw new ReaderException(String.format("%s not found.", dccManufacturersFile));
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
