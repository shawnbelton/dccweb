package uk.co.redkiteweb.dccweb.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import uk.co.redkiteweb.dccweb.data.model.Block;
import uk.co.redkiteweb.dccweb.data.model.Macro;
import uk.co.redkiteweb.dccweb.data.repositories.BlockRepository;
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
    private SimpMessagingTemplate messagingTemplate;

    @Before
    public void setup() {
        LogStore logStore = mock(LogStore.class);
        blockRepository = mock(BlockRepository.class);
        MacroService macroService = mock(MacroService.class);
        messagingTemplate = mock(SimpMessagingTemplate.class);
        blockService = new BlockService();
        blockService.setLogStore(logStore);
        blockService.setBlockRepository(blockRepository);
        blockService.setMacroService(macroService);
        blockService.setMessagingTemplate(messagingTemplate);
    }

    @Test
    public void updateNonExisting() {
        when(blockRepository.findOne(anyString())).thenReturn(null);
        blockService.updateBlock("BlockId", false);
        verify(blockRepository, times(1)).save(any(Block.class));
        verify(messagingTemplate, times(1)).convertAndSend(eq("/blocks"), any(Block.class));
    }

    @Test
    public void updateExistingBlockAsync() {
        final Block block = new Block();
        block.setOccupied(false);
        block.setMacro(mock(Macro.class));
        when(blockRepository.findOne(anyString())).thenReturn(block);
        blockService.updateBlockAsync("BlockId", true);
        verify(blockRepository, times(1)).save(eq(block));
        verify(messagingTemplate, times(1)).convertAndSend(eq("/blocks"), any(Block.class));
    }

    @Test
    public void updateExistingBlock() {
        final Block block = new Block();
        block.setOccupied(false);
        when(blockRepository.findOne(anyString())).thenReturn(block);
        blockService.updateBlock("BlockId", true);
        verify(blockRepository, times(1)).save(eq(block));
        verify(messagingTemplate, times(1)).convertAndSend(eq("/blocks"), any(Block.class));
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
