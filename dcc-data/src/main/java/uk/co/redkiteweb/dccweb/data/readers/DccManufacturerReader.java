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
        } catch (FileNotFoundException fileNotFound) {
            LOGGER.error(String.format("File %s not found.", dccManufacturersFile), fileNotFound);
        } catch (IOException ioException) {
            LOGGER.error(String.format("Unable to read %s", dccManufacturersFile), ioException);
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

    private DccManufacturer getDccManufacturer(final String[] parts) {
        final DccManufacturer dccManufacturer = new DccManufacturer();
        dccManufacturer.setManufacturerId(Integer.parseInt(parts[0]));
        dccManufacturer.setManufacturer(parts[1]);
        dccManufacturer.setCountry(parts[2]);
        return dccManufacturer;
    }

    private BufferedReader getReader() throws FileNotFoundException {
        if (bufferedReader == null) {
            bufferedReader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(dccManufacturersFile)));
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
