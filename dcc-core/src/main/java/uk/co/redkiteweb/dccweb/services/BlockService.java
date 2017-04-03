package uk.co.redkiteweb.dccweb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uk.co.redkiteweb.dccweb.data.model.Block;
import uk.co.redkiteweb.dccweb.data.repositories.BlockRepository;
import uk.co.redkiteweb.dccweb.data.service.NotificationService;

/**
 * Created by shawn on 03/04/17.
 */
@Service
public class BlockService {

    private BlockRepository blockRepository;
    private NotificationService notificationService;

    @Autowired
    public void setBlockRepository(final BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    @Autowired
    public void setNotificationService(final NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Async
    public void updateBlock(final String blockId, final Boolean occupied) {
        Block block = blockRepository.findOne(blockId);
        if (null == block) {
            block = new Block();
            block.setBlockId(blockId);
            block.setOccupied(occupied);
        } else {
            block.setOccupied(occupied);
        }
        blockRepository.save(block);
        notificationService.createNotification("BLOCK", "");
    }
}
