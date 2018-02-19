package uk.co.redkiteweb.dccweb.data.readers;

import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoderType;

/**
 * Created by shawn on 26/01/17.
 */
@Component
public class AccessoryDecoderTypeReader extends AbstractReader implements Reader<AccessoryDecoderType> {

    private static final String ACCESSORY_DECODER_TYPE_FILE = "accessoryDecoderTypes.csv";

    @Override
    public AccessoryDecoderType read() {
        return getAccessoryDecoderType(readLine());
    }

    @Override
    protected String getFileName() {
        return ACCESSORY_DECODER_TYPE_FILE;
    }

    private static AccessoryDecoderType getAccessoryDecoderType(final String readLine) {
        AccessoryDecoderType accessoryDecoderType = null;
        if (readLine != null) {
            final String[] parts = readLine.split("\\|");
            if (parts.length == 3) {
                accessoryDecoderType = getAccessoryDecoderType(parts);
            }
        }
        return accessoryDecoderType;
    }

    private static AccessoryDecoderType getAccessoryDecoderType(final String[] parts) {
        final AccessoryDecoderType accessoryDecoderType = new AccessoryDecoderType();
        accessoryDecoderType.setDecoderTypeId(Integer.parseInt(parts[0]));
        accessoryDecoderType.setDecoderType(parts[1]);
        accessoryDecoderType.setDecoderTypeCode(parts[2]);
        return accessoryDecoderType;
    }
}
