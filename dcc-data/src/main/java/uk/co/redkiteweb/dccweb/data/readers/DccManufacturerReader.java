package uk.co.redkiteweb.dccweb.data.readers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.DccManufacturer;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by shawn on 30/06/16.
 */
@Component
public class DccManufacturerReader extends AbstractReader implements Reader<DccManufacturer> {

    private static final Logger LOGGER = LogManager.getLogger(AccessoryDecoderTypeReader.class);

    private static final String DCC_MANUFACTURER_FILE = "dcc-manufacturers.csv";

    @Override
    public DccManufacturer read() {
        DccManufacturer dccManufacturer = null;
        try {
            dccManufacturer = getDccManufacturer(readLine());
        } catch (IOException ioException) {
            LOGGER.error(String.format("Unable to read %s", DCC_MANUFACTURER_FILE), ioException);
        }
        return dccManufacturer;
    }

    @Override
    protected InputStream getInputStream() {
        return this.getClass().getClassLoader().getResourceAsStream(DCC_MANUFACTURER_FILE);
    }

    private static DccManufacturer getDccManufacturer(final String readLine) {
        DccManufacturer dccManufacturer = null;
        if (readLine != null) {
            final String[] parts = readLine.split("\\|");
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

}
