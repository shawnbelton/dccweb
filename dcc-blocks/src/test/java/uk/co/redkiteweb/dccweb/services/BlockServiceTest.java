package uk.co.redkiteweb.dccweb.services;

import com.google.common.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Block;
import uk.co.redkiteweb.dccweb.data.repositories.BlockRepository;
import uk.co.redkiteweb.dccweb.events.BlockUpdateEvent;
import uk.co.redkiteweb.dccweb.store.LogStore;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 03/04/17.
 */
@RunWith(JUnit4.class)
public class BlockServiceTest {

    private BlockService blockService;
    private BlockRepository blockRepository;
    private EventBus eventBus;

    @Before
    public void setup() {
        final LogStore logStore = mock(LogStore.class);
        blockRepository = mock(BlockRepository.class);
        eventBus = mock(EventBus.class);
        blockService = new BlockService();
        blockService.setLogStore(logStore);
        blockService.setBlockRepository(blockRepository);
        blockService.setEventBus(eventBus);
    }

    @Test
    public void updateNonExisting() {
        when(blockRepository.findById(anyString())).thenReturn(Optional.empty());
        blockService.updateBlock("BlockId", false);
        verify(blockRepository, times(1)).save(any(Block.class));
        verify(eventBus, times(1)).post(any(BlockUpdateEvent.class));
    }

    @Test
    public void updateExistingBlockAsync() {
        final Block block = new Block();
        block.setOccupied(false);
        block.setMacroId(1);
        when(blockRepository.findById(anyString())).thenReturn(Optional.of(block));
        blockService.updateBlockAsync("BlockId", true);
        verify(blockRepository, times(1)).save(eq(block));
        verify(eventBus, times(1)).post(any(BlockUpdateEvent.class));
    }

    @Test
    public void updateExistingBlock() {
        final Block block = new Block();
        block.setOccupied(false);
        when(blockRepository.findById(anyString())).thenReturn(Optional.of(block));
        blockService.updateBlock("BlockId", true);
        verify(blockRepository, times(1)).save(eq(block));
        verify(eventBus, times(1)).post(any(BlockUpdateEvent.class));
    }

    @Test
    public void getAllBlocks() {
        when(blockRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(blockService.getAllBlocks());
    }

    @Test
    public void saveBlock() {
        final Block block = new Block();
        block.setBlockId("ident");
        when(blockRepository.findAll()).thenReturn(new ArrayList<>());
        blockService.saveBlock(block);
        verify(blockRepository, times(1)).save(any(Block.class));
    }

    @Test
    public void deleteBlock() {
        final Block block = new Block();
        block.setBlockId("ident");
        when(blockRepository.findAll()).thenReturn(new ArrayList<>());
        blockService.deleteBlock(block);
        verify(blockRepository, times(1)).delete(any(Block.class));
    }
}
