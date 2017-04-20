package uk.co.redkiteweb.dccweb.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Block;
import uk.co.redkiteweb.dccweb.data.repositories.BlockRepository;
import uk.co.redkiteweb.dccweb.data.service.NotificationService;
import uk.co.redkiteweb.dccweb.data.store.LogStore;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 03/04/17.
 */
@RunWith(JUnit4.class)
public class BlockServiceTest {

    private BlockService blockService;
    private BlockRepository blockRepository;
    private NotificationService notificationService;
    private LogStore logStore;

    @Before
    public void setup() {
        logStore = mock(LogStore.class);
        blockRepository = mock(BlockRepository.class);
        notificationService = mock(NotificationService.class);
        blockService = new BlockService();
        blockService.setLogStore(logStore);
        blockService.setBlockRepository(blockRepository);
        blockService.setNotificationService(notificationService);
    }

    @Test
    public void updateNonExisting() {
        when(blockRepository.findOne(anyString())).thenReturn(null);
        blockService.updateBlock("BlockId", false);
        verify(blockRepository, times(1)).save(any(Block.class));
        verify(notificationService, times(1)).createNotification(eq("BLOCK"), eq(""));
    }

    @Test
    public void updateExistingBlock() {
        final Block block = new Block();
        block.setOccupied(false);
        when(blockRepository.findOne(anyString())).thenReturn(block);
        blockService.updateBlock("BlockId", true);
        verify(blockRepository, times(1)).save(eq(block));
        verify(notificationService, times(1)).createNotification(eq("BLOCK"), eq(""));
    }

    @Test
    public void getAllBlocks() {
        when(blockRepository.findAll()).thenReturn(new ArrayList<Block>());
        assertNotNull(blockService.getAllBlocks());
    }

    @Test
    public void saveBlock() {
        final Block block = new Block();
        block.setBlockId("ident");
        when(blockRepository.findAll()).thenReturn(new ArrayList<Block>());
        blockService.saveBlock(block);
        verify(blockRepository, times(1)).save(any(Block.class));
    }

    @Test
    public void deleteBlock() {
        final Block block = new Block();
        block.setBlockId("ident");
        when(blockRepository.findAll()).thenReturn(new ArrayList<Block>());
        blockService.deleteBlock(block);
        verify(blockRepository, times(1)).delete(any(Block.class));
    }
}
