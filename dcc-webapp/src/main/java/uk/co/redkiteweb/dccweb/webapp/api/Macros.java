package uk.co.redkiteweb.dccweb.webapp.api;

import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.redkiteweb.dccweb.data.model.Macro;
import uk.co.redkiteweb.dccweb.events.MacroRunEvent;
import uk.co.redkiteweb.dccweb.services.MacroService;

import java.util.Collection;

/**
 * Created by shawn on 02/12/16.
 */
@RestController
@RequestMapping("/api/macros")
public class Macros {

    private MacroService macroService;
    private EventBus eventBus;

    @Autowired
    public void setMacroService(final MacroService macroService) {
        this.macroService = macroService;
    }

    @Autowired
    public void setEventBus(final EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody Collection<Macro> getMacros() {
        return macroService.getMacros();
    }

    @RequestMapping(value = "/{macroId}", method = RequestMethod.GET)
    public @ResponseBody Macro getMacro(@PathVariable final Integer macroId) {
        return macroService.getMacro(macroId);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody Collection<Macro> saveMacro(@RequestBody final Macro macro) {
        return macroService.saveMacro(macro);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody Collection<Macro> deleteMacro(@RequestBody final Macro macro) {
        return macroService.deleteMacro(macro);
    }

    @RequestMapping(value = "/run", method = RequestMethod.POST)
    public @ResponseBody Boolean runMacro(@RequestBody final Macro macro) {
        this.eventBus.post(new MacroRunEvent(macro));
        return Boolean.TRUE;
    }

}
