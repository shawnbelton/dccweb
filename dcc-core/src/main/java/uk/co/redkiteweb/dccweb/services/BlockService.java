package uk.co.redkiteweb.dccweb.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uk.co.redkiteweb.dccweb.data.model.Block;
import uk.co.redkiteweb.dccweb.data.repositories.BlockRepository;
import uk.co.redkiteweb.dccweb.data.service.NotificationService;
import uk.co.redkiteweb.dccweb.data.store.LogStore;

import java.util.List;

/**
 * Created by shawn on 03/04/17.
 */
@Service
public class BlockService {

    private static final Logger LOGGER = LogManager.getLogger(BlockService.class);

    private BlockRepository blockRepository;
    private NotificationService notificationService;
    private MacroService macroService;
    private LogStore logStore;

    @Autowired
    public void setBlockRepository(final BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    @Autowired
    public void setNotificationService(final NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Autowired
    public void setMacroService(final MacroService macroService) {
        this.macroService = macroService;
    }

    @Autowired
    public void setLogStore(final LogStore logStore) {
        this.logStore = logStore;
    }

    @Async
    public void updateBlockAsync(final String blockId, final Boolean occupied) {
        updateBlock(blockId, occupied);
    }

    public void updateBlock(final String blockId, final Boolean occupied) {
        Block block = blockRepository.findOne(blockId);
        if (null == block) {
            block = new Block();
            block.setBlockId(blockId);
            block.setBlockName(blockId);
            block.setOccupied(occupied);
        } else {
            block.setOccupied(occupied);
        }
        blockRepository.save(block);
        final String message = String.format("Block %s is now %s.",
                block.getBlockName(), block.getOccupied()?"Occupied":"Unoccupied");
        logStore.log("info", message);
        LOGGER.info(message);
        notificationService.createNotification("BLOCK", "");
        if (block.getMacro()!=null) {
            macroService.runMacro(block.getMacro());
        }
    }

    public List<Block> getAllBlocks() {
        return (List<Block>)blockRepository.findAll();
    }

    public List<Block> saveBlock(final Block block) {
        blockRepository.save(block);
        logStore.log("info", String.format("Block %s saved.", block.getBlockId()));
        return getAllBlocks();
    }

    public List<Block> deleteBlock(final Block block) {
        blockRepository.delete(block);
        logStore.log("info", String.format("Block %s deleted.", block.getBlockId()));
        return getAllBlocks();
    }
}
