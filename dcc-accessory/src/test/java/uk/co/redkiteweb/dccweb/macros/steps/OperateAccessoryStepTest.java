package uk.co.redkiteweb.dccweb.macros.steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.AccessoryOperation;
import uk.co.redkiteweb.dccweb.data.model.AccessoryDecoder;
import uk.co.redkiteweb.dccweb.data.repositories.AccessoryDecoderRepository;
import uk.co.redkiteweb.dccweb.macros.MacroStepItem;
import uk.co.redkiteweb.dccweb.services.AccessoryService;

import java.util.Optional;

import static org.mockito.Mockito.*;

/**
 * Created by shawn on 24/04/17.
 */
@RunWith(JUnit4.class)
public class OperateAccessoryStepTest {

    private OperateAccessoryStep operateAccessoryStep;
    private AccessoryService accessoryService;
    private AccessoryDecoderRepository accessoryDecoderRepository;
    private MacroStepItem macroStep;

    @Before
    public void setup() {
        accessoryService = mock(AccessoryService.class);
        accessoryDecoderRepository = mock(AccessoryDecoderRepository.class);
        macroStep = mock(MacroStepItem.class);
        operateAccessoryStep = new OperateAccessoryStep();
        operateAccessoryStep.setAccessoryService(accessoryService);
        operateAccessoryStep.setAccessoryDecoderRepository(accessoryDecoderRepository);
        operateAccessoryStep.setMacroStep(macroStep);
    }

    @Test
    public void operateAccessoryTest() {
        when(macroStep.getTargetId()).thenReturn(1);
        when(macroStep.getValue()).thenReturn(10);
        final AccessoryDecoder accessoryDecoder = mock(AccessoryDecoder.class);
        when(accessoryDecoder.getAddress()).thenReturn(120);
        when(accessoryDecoderRepository.findById(anyInt())).thenReturn(Optional.of(accessoryDecoder));
        operateAccessoryStep.run();
        verify(accessoryService, times(1)).operateServiceAsyc(any(AccessoryOperation.class));
    }
}
