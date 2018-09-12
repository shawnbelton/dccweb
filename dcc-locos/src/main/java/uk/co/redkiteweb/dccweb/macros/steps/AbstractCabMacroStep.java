package uk.co.redkiteweb.dccweb.macros.steps;

import org.springframework.beans.factory.annotation.Autowired;
import uk.co.redkiteweb.dccweb.data.Cab;
import uk.co.redkiteweb.dccweb.data.store.CabStore;
import uk.co.redkiteweb.dccweb.services.CabService;

public abstract class AbstractCabMacroStep extends AbstractMacroStep {

    private CabService cabService;
    private CabStore cabStore;

    @Autowired
    public void setCabService(final CabService cabService) {
        this.cabService = cabService;
    }

    @Autowired
    public void setCabStore(final CabStore cabStore) {
        this.cabStore = cabStore;
    }

    protected CabService getCabService() {
        return cabService;
    }

    protected Cab getCab() {
        return cabStore.getCab(getMacroStep().getTargetId());
    }

}
