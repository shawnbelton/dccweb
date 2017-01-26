package uk.co.redkiteweb.dccweb.data.readers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoderType;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by shawn on 26/01/17.
 */
@Component
public class AccessoryDecoderTypeReader extends AbstractReader implements Reader<AccessoryDecoderType> {

    private static final Logger LOGGER = LogManager.getLogger(AccessoryDecoderTypeReader.class);

    private static final String ACCESSORY_DECODER_TYPE_FILE = "accessoryDecoderTypes.csv";

    @Override
    public AccessoryDecoderType read() {
        AccessoryDecoderType accessoryDecoderType = null;
        try {
            accessoryDecoderType = getAccessoryDecoderType(readLine());
        } catch (IOException ioException) {
            LOGGER.error(String.format("Unable to read %s", ACCESSORY_DECODER_TYPE_FILE), ioException);
        }
        return accessoryDecoderType;
    }

    @Override
    protected InputStream getInputStream() {
        return this.getClass().getClassLoader().getResourceAsStream(ACCESSORY_DECODER_TYPE_FILE);
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
