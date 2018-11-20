package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.redkiteweb.dccweb.data.model.Loco;
import uk.co.redkiteweb.dccweb.services.LocoService;

import java.util.Collection;

/**
 * Created by shawn on 24/06/16.
 */
@RestController
@RequestMapping("/api/locos")
public class Locos {

    private LocoService locoService;

    @Autowired
    public void setLocoService(final LocoService locoService) {
        this.locoService = locoService;
    }

    @GetMapping(value = "/all")
    public @ResponseBody Collection<Loco> getAllLocos() {
        return locoService.getAllLocos();
    }

    @PostMapping(value = "/save")
    public @ResponseBody Collection<Loco> saveLoco(@RequestBody final Loco loco) {
        return locoService.saveLoco(loco);
    }

    @PostMapping(value = "/byId")
    public @ResponseBody Loco getById(@RequestBody final Integer locoId) {
        return locoService.getById(locoId);
    }
}
