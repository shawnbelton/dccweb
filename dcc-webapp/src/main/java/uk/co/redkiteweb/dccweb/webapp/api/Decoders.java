package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.redkiteweb.dccweb.data.DecoderSetting;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.model.DecoderFunction;
import uk.co.redkiteweb.dccweb.data.model.LinkedMacro;
import uk.co.redkiteweb.dccweb.services.DecoderService;

import java.util.Collection;

/**
 * Created by shawn on 07/07/16.
 */
@RestController
@RequestMapping("/api/decoders")
public class Decoders {

    private DecoderService decoderService;

    @Autowired
    public void setDecoderService(final DecoderService decoderService) {
        this.decoderService = decoderService;
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public @ResponseBody Decoder readDecoder() {
        return decoderService.readDecoder();
    }

    @RequestMapping(value = "/read/full", method = RequestMethod.GET)
    public @ResponseBody Collection<DecoderSetting> readFull() {
        return decoderService.readFull();
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public @ResponseBody Boolean writeCVs(@RequestBody final Collection<DecoderSetting> decoderSettings) {
        return decoderService.writeCVs(decoderSettings);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody Collection<Decoder> allDecoders() {
        return decoderService.allDecoders();
    }

    @RequestMapping(value = "/byId/{decoderId}", method = RequestMethod.GET)
    public @ResponseBody Decoder getById(@PathVariable final Integer decoderId) {
        return decoderService.getById(decoderId);
    }

    @RequestMapping(value = "/function/add", method = RequestMethod.POST)
    public @ResponseBody Decoder addFunction(@RequestBody final DecoderFunction decoderFunction) {
        return decoderService.addFunction(decoderFunction);
    }

    @RequestMapping(value = "/function/delete", method = RequestMethod.POST)
    public @ResponseBody Decoder deleteFunction(@RequestBody final DecoderFunction decoderFunction) {
        return decoderService.deleteFunction(decoderFunction);
    }

    @RequestMapping(value = "/macro/link", method = RequestMethod.POST)
    public @ResponseBody Decoder linkMacro(@RequestBody final LinkedMacro linkedMacro) {
        return decoderService.linkMacro(linkedMacro);
    }

    @RequestMapping(value = "/macro/unlink", method = RequestMethod.POST)
    public @ResponseBody Decoder unlinkMacro(@RequestBody final LinkedMacro linkedMacro) {
        return decoderService.unlinkMacro(linkedMacro);
    }
}
