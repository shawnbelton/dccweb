package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Block;
import uk.co.redkiteweb.dccweb.services.BlockService;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 24/11/16.
 */
@RunWith(JUnit4.class)
public class BlocksTest {

    private Blocks blocks;
    private BlockService blockService;

    @Before
    public void setup() {
        blocks = new Blocks();
        blockService = mock(BlockService.class);
        blocks.setBlockService(blockService);
    }

    @Test
    public void occupiedTest() {
        blocks.blockOccupancy("identifier", 1, true);
        verify(blockService, times(1)).updateBlockAsync(anyString(), anyBoolean());
    }

    @Test
    public void unoccupiedTest() {
        blocks.blockOccupancy("identifier", 1, false);
        verify(blockService, times(1)).updateBlockAsync(anyString(), anyBoolean());
    }

    @Test
    public void manualOccupiedTest() {
        final Block block = new Block();
        block.setOccupied(true);
        block.setBlockId("identifer");
        blocks.blockOccupancy(block);
        verify(blockService, times(1)).updateBlock(anyString(), anyBoolean());
    }

    @Test
    public void manualUnoccupiedTest() {
        final Block block = new Block();
        block.setOccupied(false);
        block.setBlockId("identifer");
        blocks.blockOccupancy(block);
        verify(blockService, times(1)).updateBlock(anyString(), anyBoolean());
    }

    @Test
    public void getAllBlocks() {
        when(blockService.getAllBlocks()).thenReturn(new ArrayList<>());
        assertNotNull(blocks.getAllBlocks());
    }

    @Test
    public void saveTest() {
        when(blockService.saveBlock(any(Block.class))).thenReturn(new ArrayList<>());
        assertNotNull(blocks.saveBlock(new Block()));
    }

    @Test
    public void deleteTest() {
        when(blockService.deleteBlock(any(Block.class))).thenReturn(new ArrayList<>());
        assertNotNull(blocks.deleteBlock(new Block()));
    }
}
