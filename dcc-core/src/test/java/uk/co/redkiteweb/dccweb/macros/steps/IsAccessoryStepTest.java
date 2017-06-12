package uk.co.redkiteweb.dccweb.macros.steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoder;
import uk.co.redkiteweb.dccweb.data.model.MacroStep;
import uk.co.redkiteweb.dccweb.data.repositories.AccessoryDecoderRepository;
import uk.co.redkiteweb.dccweb.macros.MacroContext;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 12/06/17.
 */
@RunWith(JUnit4.class)
public class IsAccessoryStepTest {

    private IsAccessoryStep isAccessoryStep;
    private AccessoryDecoderRepository accessoryDecoderRepository;
    private AccessoryDecoder accessoryDecoder;
    private MacroStep macroStep;
    private MacroContext macroContext;

    @Before
    public void setup() {
        macroStep = mock(MacroStep.class);
        accessoryDecoderRepository = mock(AccessoryDecoderRepository.class);
        accessoryDecoder = mock(AccessoryDecoder.class);
        macroContext = mock(MacroContext.class);
        isAccessoryStep = new IsAccessoryStep();
        isAccessoryStep.setMacroStep(macroStep);
        isAccessoryStep.setAccessoryDecoderRepository(accessoryDecoderRepository);
        isAccessoryStep.setMacroContext(macroContext);
        when(accessoryDecoderRepository.findOne(anyInt())).thenReturn(accessoryDecoder);
    }

    @Test
    public void testRunStepMatch() {
        when(accessoryDecoder.getCurrentValue()).thenReturn(1);
        when(macroStep.getValue()).thenReturn(1);
        isAccessoryStep.run();
        verify(macroContext, times(1)).setState(eq(true));
    }

    @Test
    public void testRunStepNoMatch() {
        when(accessoryDecoder.getCurrentValue()).thenReturn(0);
        when(macroStep.getValue()).thenReturn(1);
        isAccessoryStep.run();
        verify(macroContext, times(1)).setState(eq(false));
    }
}