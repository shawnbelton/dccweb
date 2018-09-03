package uk.co.redkiteweb.dccweb.macros.steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Block;
import uk.co.redkiteweb.dccweb.data.repositories.BlockRepository;
import uk.co.redkiteweb.dccweb.macros.MacroContext;
import uk.co.redkiteweb.dccweb.macros.MacroStepItem;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 08/05/17.
 */
@RunWith(JUnit4.class)
public class IsBlockStepTest {

    private IsBlockStep isBlockStep;
    private Block block;
    private MacroContext macroContext;
    private MacroStepItem macroStep;

    @Before
    public void setup() {
        BlockRepository blockRepository = mock(BlockRepository.class);
        block = mock(Block.class);
        macroContext = mock(MacroContext.class);
        macroStep = mock(MacroStepItem.class);
        when(blockRepository.findOne(anyString())).thenReturn(block);
        isBlockStep = new IsBlockStep();
        isBlockStep.setBlockRepository(blockRepository);
        isBlockStep.setMacroContext(macroContext);
        isBlockStep.setMacroStep(macroStep);
        when(macroStep.getBlockId()).thenReturn("block-id");
    }

    @Test
    public void blockOccupiedIsOccupiedTest() {
        when(macroStep.getValue()).thenReturn(1);
        when(block.getOccupied()).thenReturn(true);
        isBlockStep.runStep();
        verify(macroContext, times(1)).setState(eq(true));
    }

    @Test
    public void blockUnoccupiedIsOccupiedTest() {
        when(macroStep.getValue()).thenReturn(1);
        when(block.getOccupied()).thenReturn(false);
        isBlockStep.runStep();
        verify(macroContext, times(1)).setState(eq(false));
    }

    @Test
    public void blockOccupiedIsUnoccupiedTest() {
        when(macroStep.getValue()).thenReturn(0);
        when(block.getOccupied()).thenReturn(true);
        isBlockStep.runStep();
        verify(macroContext, times(1)).setState(eq(false));
    }

    @Test
    public void blockUnoccupiedIsUnoccupiedTest() {
        when(macroStep.getValue()).thenReturn(0);
        when(block.getOccupied()).thenReturn(false);
        isBlockStep.runStep();
        verify(macroContext, times(1)).setState(eq(true));
    }
}
