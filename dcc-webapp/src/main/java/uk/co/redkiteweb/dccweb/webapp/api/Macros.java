package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.redkiteweb.dccweb.data.model.Macro;
import uk.co.redkiteweb.dccweb.data.repositories.MacroRepository;
import uk.co.redkiteweb.dccweb.services.MacroService;

import java.util.List;

/**
 * Created by shawn on 02/12/16.
 */
@RestController
@RequestMapping("/api/macros")
public class Macros {

    private MacroRepository macroRepository;
    private MacroService macroService;

    @Autowired
    public void setMacroRepository(MacroRepository macroRepository) {
        this.macroRepository = macroRepository;
    }

    @Autowired
    public void setMacroService(final MacroService macroService) {
        this.macroService = macroService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody List<Macro> getMacros() {
        return (List<Macro>)this.macroRepository.findAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody List<Macro> saveMacro(@RequestBody final Macro macro) {
        this.macroRepository.save(macro);
        return getMacros();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody List<Macro> deleteMacro(@RequestBody final Macro macro) {
        this.macroRepository.delete(macro);
        return getMacros();
    }

    @RequestMapping(value = "/run", method = RequestMethod.POST)
    public @ResponseBody Boolean runMacro(@RequestBody final Macro macro) {
        this.macroService.runMacro(macro);
        return Boolean.TRUE;
    }

    @RequestMapping(value = "/{macroId}", method = RequestMethod.GET)
    public @ResponseBody Macro getMacro(@PathVariable final Integer macroId) {
        return this.macroRepository.findOne(macroId);
    }
}
