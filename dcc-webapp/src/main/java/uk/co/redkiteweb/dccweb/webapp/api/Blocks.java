package uk.co.redkiteweb.dccweb.webapp.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.redkiteweb.dccweb.data.model.Block;
import uk.co.redkiteweb.dccweb.data.repositories.BlockRepository;
import uk.co.redkiteweb.dccweb.data.store.LogStore;
import uk.co.redkiteweb.dccweb.services.BlockService;

import java.util.List;

/**
 * Created by shawn on 13/11/16.
 */
@RestController
public class Blocks {
    private static final Logger LOGGER = LogManager.getLogger(Blocks.class);

    private LogStore logStore;
    private BlockService blockService;
    private BlockRepository blockRepository;

    @Autowired
    public void setLogStore(final LogStore logStore) {
        this.logStore = logStore;
    }

    @Autowired
    public void setBlockService(final BlockService blockService) {
        this.blockService = blockService;
    }

    @Autowired
    public void setBlockRepository(final BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    @RequestMapping(value = "/block/{identifier}/{blockNumber}/occupied/{occupied}", method = RequestMethod.GET)
    public @ResponseBody Boolean blockOccupancy(@PathVariable final String identifier,
                                                @PathVariable final Integer blockNumber,
                                                @PathVariable final Boolean occupied) {
        final String message = String.format("Block Number %d of %s is now %s.", blockNumber, identifier, occupied?"Occupied":"Unoccupied");
        logStore.log("info", message);
        LOGGER.info(message);
        blockService.updateBlock(String.format("%s-%d", identifier, blockNumber), occupied);
        return Boolean.TRUE;
    }

    @RequestMapping(value = "/block/all", method = RequestMethod.GET)
    public @ResponseBody List<Block> getAllBlocks() {
        return (List<Block>)blockRepository.findAll();
    }

}
