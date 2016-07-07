package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.readers.DecoderReader;

/**
 * Created by shawn on 07/07/16.
 */
@RestController
public class Decoders {

    private DecoderReader decoderReader;

    @Autowired
    public void setDecoderReader(final DecoderReader decoderReader) {
        this.decoderReader = decoderReader;
    }

    @RequestMapping("/decoders/read")
    public Decoder readDecoder() {
        return decoderReader.readDecoderOnProgram();
    }

}
