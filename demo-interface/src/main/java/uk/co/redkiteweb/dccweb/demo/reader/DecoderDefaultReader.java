package uk.co.redkiteweb.dccweb.demo.reader;

import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.readers.AbstractReader;
import uk.co.redkiteweb.dccweb.data.readers.Reader;

/**
 * Created by shawn on 26/07/16.
 */
@Component
public class DecoderDefaultReader extends AbstractReader implements Reader<CVValue> {

    private static final String DEFAULT_DECODER_VALUES = "decoderDefaults.csv";

    @Override
    protected String getFileName() {
        return DEFAULT_DECODER_VALUES;
    }

    @Override
    public CVValue read() {
        return getCVValue(readLine());
    }

    private static CVValue getCVValue(final String readLine) {
        CVValue cvValue = null;
        if (readLine != null) {
            final String[] parts = readLine.split(",");
            if (parts.length == 2) {
                cvValue = getCVValue(parts);
            }
        }
        return cvValue;
    }

    private static CVValue getCVValue(final String[] parts) {
        final CVValue cvValue = new CVValue();
        cvValue.setNumber(Integer.parseInt(parts[0]));
        cvValue.setValue(Integer.parseInt(parts[1]));
        return cvValue;
    }

}
