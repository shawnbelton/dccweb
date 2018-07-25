package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.redkiteweb.dccweb.data.model.Loco;
import uk.co.redkiteweb.dccweb.data.repositories.LocoRepository;
import uk.co.redkiteweb.dccweb.data.store.LogStore;

import java.util.Collection;

/**
 * Created by shawn on 24/06/16.
 */
@RestController
@RequestMapping("/api/locos")
public class Locos {

    private LocoRepository locoRepository;
    private LogStore logStore;

    @Autowired
    public void setLocoRepository(final LocoRepository locoRepository) {
        this.locoRepository = locoRepository;
    }

    @Autowired
    public void setLogStore(final LogStore logStore) {
        this.logStore = logStore;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody Collection<Loco> getAllLocos() {
        return (Collection<Loco>) locoRepository.findAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody Collection<Loco> saveLoco(@RequestBody final Loco loco) {
        locoRepository.save(loco);
        logStore.log("info", String.format("Saved loco with number %s", loco.getNumber()));
        return getAllLocos();
    }

    @RequestMapping(value = "/byId", method = RequestMethod.POST)
    public @ResponseBody
    Loco getById(@RequestBody final Integer locoId) {
        return locoRepository.findOne(locoId);
    }
}
