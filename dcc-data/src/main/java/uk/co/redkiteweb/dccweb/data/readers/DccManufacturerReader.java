package uk.co.redkiteweb.dccweb.data.readers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.DccManufacturer;

/**
 * Created by shawn on 30/06/16.
 */
@Component
@Scope("prototype")
public class DccManufacturerReader implements Reader<DccManufacturer> {

    private static final String DCC_MANUFACTURER_FILE = "dcc-manufacturers.csv";

    private ResourceFileReader reader;

    @Autowired
    public void setReader(final ResourceFileReader reader) {
        this.reader = reader;
        this.reader.setResourceFile(DCC_MANUFACTURER_FILE);
    }

    @Override
    public DccManufacturer read() {
        return getDccManufacturer(reader.readLine());
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
