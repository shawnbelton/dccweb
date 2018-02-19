package uk.co.redkiteweb.dccweb.data.readers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoderTypeOperation;

/**
 * Created by shawn on 16/02/17.
 */
@Component
public class AccessoryDecoderTypeOperationReader extends AbstractReader implements Reader<AccessoryDecoderTypeOperation> {

    private static final Logger LOGGER = LogManager.getLogger(AccessoryDecoderTypeOperationReader.class);

    private static final String ACCESSORY_DECODER_TYPE_OPERATION_FILE = "accessoryDecoderTypeOperations.csv";

    @Override
    public AccessoryDecoderTypeOperation read() {
        return getAccessoryDecoderTypeOperation(readLine());
    }

    @Override
    protected String getFileName() {
        return ACCESSORY_DECODER_TYPE_OPERATION_FILE;
    }

    private static AccessoryDecoderTypeOperation getAccessoryDecoderTypeOperation(final String readLine) {
        AccessoryDecoderTypeOperation accessoryDecoderTypeOperation = null;
        if (readLine != null) {
            final String[] parts = readLine.split("\\|");
            if (parts.length == 4) {
                accessoryDecoderTypeOperation = getAccessoryDecoderTypeOperation(parts);
            }
        }
        return accessoryDecoderTypeOperation;
    }

    private static AccessoryDecoderTypeOperation getAccessoryDecoderTypeOperation(final String[] parts) {
        final AccessoryDecoderTypeOperation accessoryDecoderTypeOperation
                = new AccessoryDecoderTypeOperation();
        accessoryDecoderTypeOperation.setDecoderTypeOperationId(Integer.parseInt(parts[0]));
        accessoryDecoderTypeOperation.setDecoderTypeId(Integer.parseInt(parts[1]));
        accessoryDecoderTypeOperation.setDecoderTypeOperation(parts[2]);
        accessoryDecoderTypeOperation.setDecoderOperationValue(Integer.parseInt(parts[3]));
        return accessoryDecoderTypeOperation;
    }
}
