package uk.co.redkiteweb.dccweb.data.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoderType;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoderTypeOperation;
import uk.co.redkiteweb.dccweb.data.readers.AccessoryDecoderTypeOperationReader;
import uk.co.redkiteweb.dccweb.data.readers.AccessoryDecoderTypeReader;
import uk.co.redkiteweb.dccweb.data.repositories.AccessoryDecoderTypeOperationRepository;
import uk.co.redkiteweb.dccweb.data.repositories.AccessoryDecoderTypeRepository;

/**
 * Created by shawn on 21/02/17.
 */
@Component("AccessoryDecoderTypes")
public class AccessoryDecoderTypeLoader implements Loader {

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
        loadAccessoryDecoderTypes();
        loadAccessoryDecoderTypeOperations();
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
