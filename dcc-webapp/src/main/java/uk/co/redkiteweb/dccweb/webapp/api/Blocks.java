package uk.co.redkiteweb.dccweb.webapp.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.redkiteweb.dccweb.data.store.LogStore;

/**
 * Created by shawn on 13/11/16.
 */
@RestController
public class Blocks {
    private static final Logger LOGGER = LogManager.getLogger(Blocks.class);

    private LogStore logStore;

    @Autowired
    public void setLogStore(final LogStore logStore) {
        this.logStore = logStore;
    }

    @RequestMapping(value = "/block/{identifier}/{blockNumber}/occupied/{occupied}", method = RequestMethod.GET)
    public @ResponseBody Boolean blockOccupancy(@PathVariable final String identifier,
                                                @PathVariable final Integer blockNumber,
                                                @PathVariable final Boolean occupied) {
        final String message = String.format("Block Number %d of %s is now %s.", blockNumber, identifier, occupied?"Occupied":"Unoccupied");
        logStore.log("info", message);
        LOGGER.info(message);
        return Boolean.TRUE;
    }

}
