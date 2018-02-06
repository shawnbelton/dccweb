package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.redkiteweb.dccweb.data.DecoderSetting;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.model.DecoderFunction;
import uk.co.redkiteweb.dccweb.data.model.LinkedMacro;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderFunctionRepository;
import uk.co.redkiteweb.dccweb.data.repositories.DecoderRepository;
import uk.co.redkiteweb.dccweb.data.repositories.LinkedMacroRepository;
import uk.co.redkiteweb.dccweb.data.store.LogStore;
import uk.co.redkiteweb.dccweb.readers.DecoderReaderFactory;

import java.util.List;

/**
 * Created by shawn on 07/07/16.
 */
@RestController
@RequestMapping("/api/decoders")
public class Decoders {

    private DecoderReaderFactory decoderReaderFactory;
    private DecoderRepository decoderRepository;
    private DecoderFunctionRepository decoderFunctionRepository;
    private LinkedMacroRepository linkedMacroRepository;
    private LogStore logStore;

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

    @Autowired
    public void setLogStore(final LogStore logStore) {
        this.logStore = logStore;
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public @ResponseBody Decoder readDecoder() {
        return decoderReaderFactory.createInstance().readDecoderOnProgram();
    }

    @RequestMapping(value = "/read/full", method = RequestMethod.GET)
    public @ResponseBody List<DecoderSetting> readFull() {
        return decoderReaderFactory.createInstance().readFullOnProgram();
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public @ResponseBody Boolean writeCVs(@RequestBody final List<DecoderSetting> decoderSettings) {
        logStore.log("info", String.format("decodersettings %d", decoderSettings.size()));
        return Boolean.TRUE;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody List<Decoder> allDecoders() {
        return (List<Decoder>)decoderRepository.findAll();
    }

    @RequestMapping(value = "/byId/{decoderId}", method = RequestMethod.GET)
    public @ResponseBody Decoder getById(@PathVariable final Integer decoderId) {
        return decoderRepository.findOne(decoderId);
    }

    @RequestMapping(value = "/function/add", method = RequestMethod.POST)
    public @ResponseBody Decoder addFunction(@RequestBody final DecoderFunction decoderFunction) {
        decoderFunctionRepository.save(decoderFunction);
        logStore.log("info", String.format("Decoder function %s with number %d added.", decoderFunction.getName(), decoderFunction.getNumber()));
        return decoderRepository.findOne(decoderFunction.getDecoderId());
    }

    @RequestMapping(value = "/function/delete", method = RequestMethod.POST)
    public @ResponseBody Decoder deleteFunction(@RequestBody final DecoderFunction decoderFunction) {
        decoderFunctionRepository.delete(decoderFunction);
        logStore.log("info", String.format("Decoder function %s with number %d removed.", decoderFunction.getName(), decoderFunction.getNumber()));
        return decoderRepository.findOne(decoderFunction.getDecoderId());
    }

    @RequestMapping(value = "/macro/link", method = RequestMethod.POST)
    public @ResponseBody Decoder linkMacro(@RequestBody final LinkedMacro linkedMacro) {
        linkedMacroRepository.save(linkedMacro);
        logStore.log("info", String.format("Macro %s linked.", linkedMacro.getMacro().getName()));
        return decoderRepository.findOne(linkedMacro.getDecoderId());
    }

    @RequestMapping(value = "/macro/unlink", method = RequestMethod.POST)
    public @ResponseBody Decoder unlinkMacro(@RequestBody final LinkedMacro linkedMacro) {
        linkedMacroRepository.delete(linkedMacro);
        logStore.log("info", String.format("Macro %s unlinked.", linkedMacro.getMacro().getName()));
        return decoderRepository.findOne(linkedMacro.getDecoderId());
    }
}
