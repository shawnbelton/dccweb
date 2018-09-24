package uk.co.redkiteweb.dccweb.data.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by shawn on 03/04/17.
 */
@RunWith(JUnit4.class)
public class BlockTest {

    private Block block;

    @Before
    public void setup() {
        block = new Block();
        block.setBlockId("12345678-1");
        block.setBlockName("Block Name");
        block.setOccupied(Boolean.TRUE);
        block.setMacroId(1);
    }

    @Test
    public void testId() {
        assertEquals("12345678-1", block.getBlockId());
    }

    @Test
    public void testBlockName() {
        assertEquals("Block Name", block.getBlockName());
    }

    @Test
    public void testOccupied() {
        assertEquals(true, block.getOccupied());
    }

    @Test
    public void testMacro() {
        assertNotNull(block.getMacroId());
    }
}
