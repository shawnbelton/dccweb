package uk.co.redkiteweb.dccweb.events;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Block;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class BlockUpdateEventTest {

    private BlockUpdateEvent event;

    @Before
    public void setup() {
        final Block block = mock(Block.class);
        when(block.getMacroId()).thenReturn(1);
        event = new BlockUpdateEvent(block);
    }

    @Test
    public void testUrl() {
        assertEquals("/blocks", event.getUrl());
    }

    @Test
    public void testMacro() {
        assertNotNull(event.getMacroId());
    }

    @Test
    public void testObject() {
        assertNotNull(event.sendObject());
    }
}
