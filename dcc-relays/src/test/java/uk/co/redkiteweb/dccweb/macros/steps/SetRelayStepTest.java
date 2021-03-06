package uk.co.redkiteweb.dccweb.macros.steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.macros.MacroStepItem;
import uk.co.redkiteweb.dccweb.services.RelayControllerService;

import static org.mockito.Mockito.*;

/**
 * Created by shawn on 12/06/17.
 */
@RunWith(JUnit4.class)
public class SetRelayStepTest {

    private SetRelayStep setRelayStep;
    private MacroStepItem macroStep;
    private RelayControllerService relayControllerService;

    @Before
    public void setup() {
        relayControllerService = mock(RelayControllerService.class);
        macroStep = mock(MacroStepItem.class);
        setRelayStep = new SetRelayStep();
        setRelayStep.setMacroStep(macroStep);
        setRelayStep.setRelayControllerService(relayControllerService);
    }

    @Test
    public void setRelayTest() {
        when(macroStep.getValue()).thenReturn(1);
        setRelayStep.run();
        verify(relayControllerService, times(1)).setRelay(anyString(), anyInt());
    }

    @Test
    public void unsetRelayTest() {
        when(macroStep.getValue()).thenReturn(0);
        setRelayStep.run();
        verify(relayControllerService, times(1)).unsetRelay(anyString(), anyInt());
    }
}
