package uk.co.redkiteweb.dccweb.data.readers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoderType;

/**
 * Created by shawn on 26/01/17.
 */
@Component
@Scope("prototype")
public class AccessoryDecoderTypeReader implements Reader<AccessoryDecoderType> {

    private static final String ACCESSORY_DECODER_TYPE_FILE = "accessoryDecoderTypes.csv";

    private ResourceFileReader reader;

    @Autowired
    public void setReader(final ResourceFileReader reader) {
        this.reader = reader;
        this.reader.setResourceFile(ACCESSORY_DECODER_TYPE_FILE);
    }

    @Override
    public AccessoryDecoderType read() {
        return getAccessoryDecoderType(reader.readLine());
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
