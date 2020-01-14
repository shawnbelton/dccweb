package uk.co.redkiteweb.dccweb.data.loaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoderType;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoderTypeOperation;
import uk.co.redkiteweb.dccweb.data.readers.AccessoryDecoderTypeOperationReader;
import uk.co.redkiteweb.dccweb.data.readers.AccessoryDecoderTypeReader;
import uk.co.redkiteweb.dccweb.data.readers.ReaderException;
import uk.co.redkiteweb.dccweb.data.repositories.AccessoryDecoderTypeOperationRepository;
import uk.co.redkiteweb.dccweb.data.repositories.AccessoryDecoderTypeRepository;

/**
 * Created by shawn on 21/02/17.
 */
@Component("AccessoryDecoderTypes")
@Scope("prototype")
public class AccessoryDecoderTypeLoader implements Loader {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessoryDecoderTypeLoader.class);

    private AccessoryDecoderTypeReader accessoryDecoderTypeReader;
    private AccessoryDecoderTypeRepository accessoryDecoderTypeRepository;
    private AccessoryDecoderTypeOperationReader accessoryDecoderTypeOperationReader;
    private AccessoryDecoderTypeOperationRepository accessoryDecoderTypeOperationRepository;

    @Autowired
    public void setAccessoryDecoderTypeReader(final AccessoryDecoderTypeReader accessoryDecoderTypeReader) {
        this.accessoryDecoderTypeReader = accessoryDecoderTypeReader;
    }

    @Autowired
    public void setAccessoryDecoderTypeRepository(AccessoryDecoderTypeRepository accessoryDecoderTypeRepository) {
        this.accessoryDecoderTypeRepository = accessoryDecoderTypeRepository;
    }

    @Autowired
    public void setAccessoryDecoderTypeOperationReader(AccessoryDecoderTypeOperationReader accessoryDecoderTypeOperationReader) {
        this.accessoryDecoderTypeOperationReader = accessoryDecoderTypeOperationReader;
    }

    @Autowired
    public void setAccessoryDecoderTypeOperationRepository(AccessoryDecoderTypeOperationRepository accessoryDecoderTypeOperationRepository) {
        this.accessoryDecoderTypeOperationRepository = accessoryDecoderTypeOperationRepository;
    }

    @Override
    public void load() {
        try {
            loadAccessoryDecoderTypes();
            loadAccessoryDecoderTypeOperations();
        } catch (ReaderException exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    private void loadAccessoryDecoderTypes() {
        AccessoryDecoderType accessoryDecoderType = accessoryDecoderTypeReader.read();
        while(accessoryDecoderType!=null) {
            accessoryDecoderTypeRepository.save(accessoryDecoderType);
            accessoryDecoderType = accessoryDecoderTypeReader.read();
        }
    }

    private void loadAccessoryDecoderTypeOperations() {
        AccessoryDecoderTypeOperation accessoryDecoderTypeOperation = accessoryDecoderTypeOperationReader.read();
        while(accessoryDecoderTypeOperation!=null) {
            accessoryDecoderTypeOperationRepository.save(accessoryDecoderTypeOperation);
            accessoryDecoderTypeOperation = accessoryDecoderTypeOperationReader.read();
        }
    }
}
