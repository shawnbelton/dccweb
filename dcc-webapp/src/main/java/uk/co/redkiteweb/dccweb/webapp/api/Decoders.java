package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.model.DecoderFunction;
import uk.co.redkiteweb.dccweb.data.model.LinkedMacro;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderFunctionRepository;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderRepository;
import uk.co.redkiteweb.dccweb.data.repositories.LinkedMacroRepository;
import uk.co.redkiteweb.dccweb.readers.DecoderReaderFactory;

import java.util.List;

/**
 * Created by shawn on 07/07/16.
 */
@RestController
public class Decoders {

    private DecoderReaderFactory decoderReaderFactory;
    private DecoderRepository decoderRepository;
    private DecoderFunctionRepository decoderFunctionRepository;
    private LinkedMacroRepository linkedMacroRepository;

    @Autowired
    public void setDecoderRepository(final DecoderRepository decoderRepository) {
        this.decoderRepository = decoderRepository;
    }

    @Autowired
    public void setDecoderFunctionRepository(final DecoderFunctionRepository decoderFunctionRepository) {
        this.decoderFunctionRepository = decoderFunctionRepository;
    }

    @Autowired
    public void setLinkedMacroRepository(final LinkedMacroRepository linkedMacroRepository) {
        this.linkedMacroRepository = linkedMacroRepository;
    }

    @Autowired
    public void setDecoderReaderFactory(final DecoderReaderFactory decoderReaderFactory) {
        this.decoderReaderFactory = decoderReaderFactory;
    }

    @RequestMapping("/decoders/read")
    public @ResponseBody Decoder readDecoder() {
        return decoderReaderFactory.createInstance().readDecoderOnProgram();
    }

    @RequestMapping("/decoders/all")
    public @ResponseBody List<Decoder> allDecoders() {
        return (List<Decoder>)decoderRepository.findAll();
    }

    @RequestMapping(value = "/decoders/byId/{decoderId}", method = RequestMethod.GET)
    public @ResponseBody Decoder getById(@PathVariable final Integer decoderId) {
        return decoderRepository.findOne(decoderId);
    }

    @RequestMapping(value = "/decoders/function/add", method = RequestMethod.POST)
    public @ResponseBody Decoder addFunction(@RequestBody final DecoderFunction decoderFunction) {
        decoderFunctionRepository.save(decoderFunction);
        return decoderRepository.findOne(decoderFunction.getDecoderId());
    }

    @RequestMapping(value = "/decoders/function/delete", method = RequestMethod.POST)
    public @ResponseBody Decoder deleteFunction(@RequestBody final DecoderFunction decoderFunction) {
        decoderFunctionRepository.delete(decoderFunction);
        return decoderRepository.findOne(decoderFunction.getDecoderId());
    }

    @RequestMapping(value = "/decoders/macro/link", method = RequestMethod.POST)
    public @ResponseBody Decoder linkMacro(@RequestBody final LinkedMacro linkedMacro) {
        linkedMacroRepository.save(linkedMacro);
        return decoderRepository.findOne(linkedMacro.getDecoderId());
    }

    @RequestMapping(value = "/decoders/macro/unlink", method = RequestMethod.POST)
    public @ResponseBody Decoder unlinkMacro(@RequestBody final LinkedMacro linkedMacro) {
        linkedMacroRepository.delete(linkedMacro);
        return decoderRepository.findOne(linkedMacro.getDecoderId());
    }
}
