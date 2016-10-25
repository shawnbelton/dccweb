package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.model.DecoderFunction;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderFunctionRepository;
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
    private DecoderFunctionRepository decoderFunctionRepository;

    @Autowired
    public void setDecoderRepository(final DecoderRepository decoderRepository) {
        this.decoderRepository = decoderRepository;
    }

    @Autowired
    public void setDecoderFunctionRepository(final DecoderFunctionRepository decoderFunctionRepository) {
        this.decoderFunctionRepository = decoderFunctionRepository;
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

    @RequestMapping(value = "/decoders/byId", method = RequestMethod.POST)
    public @ResponseBody Decoder getById(@RequestBody final Integer decoderId) {
        return decoderRepository.findOne(decoderId);
    }

    @RequestMapping(value = "/decoders/function/add", method = RequestMethod.POST)
    public @ResponseBody Decoder addFunction(@RequestBody final DecoderFunction decoderFunction) {
        decoderFunctionRepository.save(decoderFunction);
        return decoderRepository.findOne(decoderFunction.getDecoderId());
    }
}
