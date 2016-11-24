package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.store.LogStore;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 24/11/16.
 */
@RunWith(JUnit4.class)
public class BlocksTest {

    private Blocks blocks;
    private LogStore logStore;

    @Before
    public void setup() {
        blocks = new Blocks();
        logStore = mock(LogStore.class);
        blocks.setLogStore(logStore);
    }

    @Test
    public void occupancyTest() {
        blocks.blockOccupancy(1, true);
        verify(logStore, times(1)).log(anyString(), anyString());
    }
}
