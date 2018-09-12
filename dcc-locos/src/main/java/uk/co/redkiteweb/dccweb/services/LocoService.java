package uk.co.redkiteweb.dccweb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.redkiteweb.dccweb.data.model.Loco;
import uk.co.redkiteweb.dccweb.data.repositories.LocoRepository;
import uk.co.redkiteweb.dccweb.store.LogStore;

import java.util.Collection;

@Service
public class LocoService {

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

    public Collection<Loco> getAllLocos() {
        return (Collection<Loco>)locoRepository.findAll();
    }

    public Loco getById(final Integer locoId) {
        return locoRepository.findById(locoId).orElse(null);
    }

    public Collection<Loco> saveLoco(final Loco loco) {
        locoRepository.save(loco);
        logStore.log("info", String.format("Saved loco with number %s", loco.getNumber()));
        return getAllLocos();
    }

}
