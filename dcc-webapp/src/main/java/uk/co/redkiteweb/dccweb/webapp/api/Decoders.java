package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderRepository;
import uk.co.redkiteweb.dccweb.readers.DecoderReader;

import java.util.List;

/**
 * Created by shawn on 07/07/16.
 */
@RestController
public class Decoders {

    private DecoderReader decoderReader;
    private DecoderRepository decoderRepository;

    @Autowired
    public void setDecoderRepository(final DecoderRepository decoderRepository) {
        this.decoderRepository = decoderRepository;
    }

    @Autowired
    public void setDecoderReader(final DecoderReader decoderReader) {
        this.decoderReader = decoderReader;
    }

    @RequestMapping("/decoders/read")
    public @ResponseBody Decoder readDecoder() {
        return decoderReader.readDecoderOnProgram();
    }

    @RequestMapping("/decoders/all")
    public @ResponseBody List<Decoder> allDecoders() {
        return (List<Decoder>)decoderRepository.findAll();
    }

}
