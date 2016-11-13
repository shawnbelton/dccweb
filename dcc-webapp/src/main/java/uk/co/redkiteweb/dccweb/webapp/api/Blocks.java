package uk.co.redkiteweb.dccweb.webapp.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

/**
 * Created by shawn on 13/11/16.
 */
@RestController
public class Blocks {
    private static final Logger LOGGER = LogManager.getLogger(Blocks.class);

    @RequestMapping(value = "/block/{blockNumber}/occupied/{occupied}", method = RequestMethod.GET)
    public @ResponseBody Boolean blockOccupancy(@PathVariable final Integer blockNumber,
                                                @PathVariable final Boolean occupied) {
        LOGGER.info(String.format("Block Number %d is now %s", blockNumber, occupied));
        return Boolean.TRUE;
    }

}
