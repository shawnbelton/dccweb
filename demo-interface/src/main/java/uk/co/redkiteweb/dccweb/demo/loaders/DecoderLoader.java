package uk.co.redkiteweb.dccweb.demo.loaders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.loaders.Loader;
import uk.co.redkiteweb.dccweb.data.readers.ReaderException;
import uk.co.redkiteweb.dccweb.demo.reader.CVValue;
import uk.co.redkiteweb.dccweb.demo.reader.DecoderDefaultReader;
import uk.co.redkiteweb.dccweb.demo.registers.DecoderRegister;

/**
 * Created by shawn on 26/07/16.
 */
@Component
@Scope("prototype")
public class DecoderLoader implements Loader {

    private static final Logger LOGGER = LogManager.getLogger(DecoderLoader.class);

    private DecoderRegister decoderRegister;
    private DecoderDefaultReader decoderDefaultReader;

    @Autowired
    public void setDecoderRegister(final DecoderRegister decoderRegister) {
        this.decoderRegister = decoderRegister;
    }

    @Autowired
    public void setDecoderDefaultReader(final DecoderDefaultReader decoderDefaultReader) {
        this.decoderDefaultReader = decoderDefaultReader;
    }

    @Override
    public void load() {
        CVValue cvValue;
        decoderRegister.initialise();
        try {
            cvValue = decoderDefaultReader.read();
            while (cvValue != null) {
                decoderRegister.setCV(cvValue.getNumber(), cvValue.getValue());
                cvValue = decoderDefaultReader.read();
            }
        } catch (ReaderException exception) {
            LOGGER.error(exception.getMessage());
        }
    }
}
