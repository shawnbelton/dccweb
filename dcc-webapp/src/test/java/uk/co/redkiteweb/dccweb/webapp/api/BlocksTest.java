package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.store.LogStore;
import uk.co.redkiteweb.dccweb.services.BlockService;

import static org.mockito.Mockito.*;

/**
 * Created by shawn on 24/11/16.
 */
@RunWith(JUnit4.class)
public class BlocksTest {

    private Blocks blocks;
    private LogStore logStore;
    private BlockService blockService;

    @Before
    public void setup() {
        blocks = new Blocks();
        logStore = mock(LogStore.class);
        blockService = mock(BlockService.class);
        blocks.setLogStore(logStore);
        blocks.setBlockService(blockService);
    }

    @Test
    public void occupiedTest() {
        blocks.blockOccupancy("identifier", 1, true);
        verify(logStore, times(1)).log(anyString(), anyString());
        verify(blockService, times(1)).updateBlock(anyString(), anyBoolean());
    }

    @Test
    public void unoccupiedTest() {
        blocks.blockOccupancy("identifier", 1, false);
        verify(logStore, times(1)).log(anyString(), anyString());
        verify(blockService, times(1)).updateBlock(anyString(), anyBoolean());
    }
}
