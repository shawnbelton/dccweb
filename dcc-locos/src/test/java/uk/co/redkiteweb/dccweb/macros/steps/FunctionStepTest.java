package uk.co.redkiteweb.dccweb.macros.steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.Cab;
import uk.co.redkiteweb.dccweb.data.CabFunction;
import uk.co.redkiteweb.dccweb.data.CabFunctionComparator;
import uk.co.redkiteweb.dccweb.data.store.CabStore;
import uk.co.redkiteweb.dccweb.macros.MacroStepItem;
import uk.co.redkiteweb.dccweb.services.CabService;

import java.util.TreeSet;

import static org.mockito.Mockito.*;

/**
 * Created by shawn on 06/12/16.
 */
@RunWith(JUnit4.class)
public class FunctionStepTest {

    private FunctionStep functionStep;
    private MacroStepItem step;
    private CabStore cabStore;
    private CabService cabService;

    @Before
    public void setup() {
        cabStore = mock(CabStore.class);
        step = mock(MacroStepItem.class);
        when(step.getNumber()).thenReturn(1);
        cabService = mock(CabService.class);
        functionStep = new FunctionStep();
        functionStep.setMacroStep(step);
        functionStep.setCabStore(cabStore);
        functionStep.setCabService(cabService);
    }

    @Test
    public void testRun() {
        when(step.getFunctionNumber()).thenReturn(1);
        when(step.getFunctionStatus()).thenReturn("true");
        final Cab cab = new Cab();
        cab.setCabFunctions(new TreeSet<>(new CabFunctionComparator()));
        final CabFunction cabFunction = new CabFunction();
        cabFunction.setNumber(1);
        cab.getCabFunctions().add(cabFunction);
        final CabFunction cabFunction2 = new CabFunction();
        cabFunction2.setNumber(2);
        cab.getCabFunctions().add(cabFunction2);
        when(cabStore.getCab(anyInt())).thenReturn(cab);
        functionStep.runStep();
        verify(cabService, times(1)).updateCabFunctions(any(Cab.class));
    }
}
