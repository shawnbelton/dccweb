package uk.co.redkiteweb.dccweb.macros.steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.Cab;
import uk.co.redkiteweb.dccweb.data.store.CabStore;
import uk.co.redkiteweb.dccweb.macros.MacroStepItem;
import uk.co.redkiteweb.dccweb.services.CabService;

import static org.mockito.Mockito.*;

/**
 * Created by shawn on 07/12/16.
 */
@RunWith(JUnit4.class)
public class SetSpeedStepTest {

    private SetSpeedStep setSpeedStep;
    private CabStore cabStore;
    private CabService cabService;

    @Before
    public void setup() {
        cabStore = mock(CabStore.class);
        cabService = mock(CabService.class);
        final MacroStepItem macroStep = mock(MacroStepItem.class);
        setSpeedStep = new SetSpeedStep();
        setSpeedStep.setCabStore(cabStore);
        setSpeedStep.setCabService(cabService);
        setSpeedStep.setMacroStep(macroStep);
    }

    @Test
    public void testSetSpeed() {
        when(cabStore.getCab(anyInt())).thenReturn(new Cab());
        setSpeedStep.run();
        verify(cabService, times(1)).updateCab(any(Cab.class));
    }
}
