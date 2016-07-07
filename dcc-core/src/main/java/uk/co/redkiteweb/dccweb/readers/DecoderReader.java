package uk.co.redkiteweb.dccweb.readers;

import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.Decoder;

/**
 * Created by shawn on 07/07/16.
 */
@Component
public class DecoderReader {

    public Decoder readDecoderOnProgram() {
        return new Decoder();
    }

}
