package uk.co.redkiteweb.dccweb.webapp.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.redkiteweb.dccweb.data.Cab;
import uk.co.redkiteweb.dccweb.data.model.Train;
import uk.co.redkiteweb.dccweb.data.store.CabStore;
import uk.co.redkiteweb.dccweb.services.AsyncCabService;

/**
 * Created by shawn on 12/09/16.
 */
@RestController
@RequestMapping("/api/trains/cab")
public class CabInterface {

    private static final Logger LOGGER = LogManager.getLogger(CabInterface.class);

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

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Cab getCab(@RequestBody final Train train) {
        return cabStore.getCab(train);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody Boolean updateCab(@RequestBody final Cab cab) {
        LOGGER.info(String.format("Updating %s to %d speed %s direction.", cab.getTrain().getNumber(), cab.getSpeed(), cab.getDirection()));
        cabService.updateCab(cab);
        cabStore.putCab(cab);
        return Boolean.TRUE;
    }

    @RequestMapping(value = "/updateFunction", method = RequestMethod.POST)
    public @ResponseBody Boolean updateCabFunction(@RequestBody final Cab cab) {
        LOGGER.info(String.format("Updating functions on %s", cab.getTrain().getNumber()));
        cabService.updateCabFunctions(cab);
        cabStore.putCab(cab);
        return Boolean.TRUE;
    }
}
