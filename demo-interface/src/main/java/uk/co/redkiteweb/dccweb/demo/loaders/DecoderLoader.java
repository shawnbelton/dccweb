package uk.co.redkiteweb.dccweb.demo.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.loaders.Loader;
import uk.co.redkiteweb.dccweb.demo.reader.CVValue;
import uk.co.redkiteweb.dccweb.demo.reader.DecoderDefaultReader;
import uk.co.redkiteweb.dccweb.demo.registers.DecoderRegister;

/**
 * Created by shawn on 26/07/16.
 */
@Component
public class DecoderLoader implements Loader {

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
        cvValue = decoderDefaultReader.read();
        while(cvValue!=null) {
            decoderRegister.setCV(cvValue.getNumber(), cvValue.getValue());
            cvValue = decoderDefaultReader.read();
        }
    }
}
