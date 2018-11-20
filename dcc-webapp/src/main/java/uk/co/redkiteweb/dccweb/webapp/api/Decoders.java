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

    @GetMapping(value = "/read")
    public @ResponseBody Decoder readDecoder() {
        return decoderService.readDecoder();
    }

    @GetMapping(value = "/read/full")
    public @ResponseBody Collection<DecoderSetting> readFull() {
        return decoderService.readFull();
    }

    @PostMapping(value = "/write")
    public @ResponseBody Boolean writeCVs(@RequestBody final Collection<DecoderSetting> decoderSettings) {
        return decoderService.writeCVs(decoderSettings);
    }

    @GetMapping(value = "/all")
    public @ResponseBody Collection<Decoder> allDecoders() {
        return decoderService.allDecoders();
    }

    @GetMapping(value = "/byId/{decoderId}")
    public @ResponseBody Decoder getById(@PathVariable final Integer decoderId) {
        return decoderService.getById(decoderId);
    }

    @PostMapping(value = "/function/add")
    public @ResponseBody Decoder addFunction(@RequestBody final DecoderFunction decoderFunction) {
        return decoderService.addFunction(decoderFunction);
    }

    @PostMapping(value = "/function/delete")
    public @ResponseBody Decoder deleteFunction(@RequestBody final DecoderFunction decoderFunction) {
        return decoderService.deleteFunction(decoderFunction);
    }

    @PostMapping(value = "/macro/link")
    public @ResponseBody Decoder linkMacro(@RequestBody final LinkedMacro linkedMacro) {
        return decoderService.linkMacro(linkedMacro);
    }

    @PostMapping(value = "/macro/unlink")
    public @ResponseBody Decoder unlinkMacro(@RequestBody final LinkedMacro linkedMacro) {
        return decoderService.unlinkMacro(linkedMacro);
    }
}
