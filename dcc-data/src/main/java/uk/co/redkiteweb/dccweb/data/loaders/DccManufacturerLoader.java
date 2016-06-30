package uk.co.redkiteweb.dccweb.data.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.DccManufacturer;
import uk.co.redkiteweb.dccweb.data.readers.Reader;
import uk.co.redkiteweb.dccweb.data.repositories.DccManufacturerRepository;

/**
 * Created by shawn on 30/06/16.
 */
@Component("DccManufacturer")
public class DccManufacturerLoader implements Loader {

    private DccManufacturerRepository dccManufacturerRepository;

    private Reader<DccManufacturer> dccManufacturerReader;

    @Autowired
    public void setDccManufacturerRepository(final DccManufacturerRepository dccManufacturerRepository) {
        this.dccManufacturerRepository = dccManufacturerRepository;
    }

    @Autowired
    public void setDccManufacturerReader(final Reader<DccManufacturer> dccManufacturerReader) {
        this.dccManufacturerReader = dccManufacturerReader;
    }

    @Override
    public void load() {
        DccManufacturer dccManufacturer;
        dccManufacturer = dccManufacturerReader.read();
        while(dccManufacturer!=null) {
            dccManufacturerRepository.save(dccManufacturer);
            dccManufacturer = dccManufacturerReader.read();
        }
    }

}
