package uk.co.redkiteweb.dccweb.data.readers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoderTypeOperation;

/**
 * Created by shawn on 16/02/17.
 */
@Component
@Scope("prototype")
public class AccessoryDecoderTypeOperationReader implements Reader<AccessoryDecoderTypeOperation> {

    private static final String ACCESSORY_DECODER_TYPE_OPERATION_FILE = "accessoryDecoderTypeOperations.csv";

    private ResourceFileReader reader;

    @Autowired
    public void setReader(final ResourceFileReader reader) {
        this.reader = reader;
        this.reader.setResourceFile(ACCESSORY_DECODER_TYPE_OPERATION_FILE);
    }

    @Override
    public AccessoryDecoderTypeOperation read() {
        return getAccessoryDecoderTypeOperation(reader.readLine());
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
