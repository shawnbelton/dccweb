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

    @GetMapping(value = "/all")
    public @ResponseBody Collection<Macro> getMacros() {
        return macroService.getMacros();
    }

    @GetMapping(value = "/{macroId}")
    public @ResponseBody Macro getMacro(@PathVariable final Integer macroId) {
        return macroService.getMacro(macroId);
    }

    @PostMapping(value = "/save")
    public @ResponseBody Collection<Macro> saveMacro(@RequestBody final Macro macro) {
        return macroService.saveMacro(macro);
    }

    @PostMapping(value = "/delete")
    public @ResponseBody Collection<Macro> deleteMacro(@RequestBody final Macro macro) {
        return macroService.deleteMacro(macro);
    }

    @PostMapping(value = "/run")
    public @ResponseBody Boolean runMacro(@RequestBody final Macro macro) {
        this.eventBus.post(new MacroRunEvent(macro.getMacroId()));
        return Boolean.TRUE;
    }

}
