package uk.co.redkiteweb.dccweb.webapp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.redkiteweb.dccweb.data.Cab;
import uk.co.redkiteweb.dccweb.data.model.Loco;
import uk.co.redkiteweb.dccweb.data.store.CabStore;
import uk.co.redkiteweb.dccweb.services.AsyncCabService;

/**
 * Created by shawn on 12/09/16.
 */
@RestController
@RequestMapping("/api/locos/cab")
public class CabInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(CabInterface.class);

    private CabStore cabStore;
    private AsyncCabService cabService;

    @Autowired
    public void setCabStore(final CabStore cabStore) {
        this.cabStore = cabStore;
    }

    @Autowired
    public void setCabService(final AsyncCabService cabService) {
        this.cabService = cabService;
    }

    @PostMapping()
    public @ResponseBody Cab getCab(@RequestBody final Loco loco) {
        return cabStore.getCab(loco);
    }

    @PostMapping(value = "/update")
    public @ResponseBody Boolean updateCab(@RequestBody final Cab cab) {
        LOGGER.info(String.format("Updating %s to %d speed %s direction.", cab.getLoco().getNumber(), cab.getSpeed(), cab.getDirection()));
        cabService.updateCab(cab);
        cabStore.putCab(cab);
        return Boolean.TRUE;
    }

    @PostMapping(value = "/updateFunction")
    public @ResponseBody Boolean updateCabFunction(@RequestBody final Cab cab) {
        LOGGER.info(String.format("Updating functions on %s", cab.getLoco().getNumber()));
        cabService.updateCabFunctions(cab);
        cabStore.putCab(cab);
        return Boolean.TRUE;
    }
}
