package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.redkiteweb.dccweb.data.model.Block;
import uk.co.redkiteweb.dccweb.services.BlockService;

import java.util.List;

/**
 * Created by shawn on 13/11/16.
 */
@RestController
public class Blocks {

    private BlockService blockService;

    @Autowired
    public void setBlockService(final BlockService blockService) {
        this.blockService = blockService;
    }

    @RequestMapping(value = "/block/{identifier}/{blockNumber}/occupied/{occupied}", method = RequestMethod.GET)
    public @ResponseBody Boolean blockOccupancy(@PathVariable final String identifier,
                                                @PathVariable final Integer blockNumber,
                                                @PathVariable final Boolean occupied) {
        blockService.updateBlock(String.format("%s-%d", identifier, blockNumber), occupied);
        return Boolean.TRUE;
    }

    @RequestMapping(value = "/block/all", method = RequestMethod.GET)
    public @ResponseBody List<Block> getAllBlocks() {
        return blockService.getAllBlocks();
    }

    @RequestMapping(value = "/block/save", method = RequestMethod.POST)
    public @ResponseBody List<Block> saveBlock(@RequestBody final Block block) {
        return blockService.saveBlock(block);
    }

}
