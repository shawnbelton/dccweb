package uk.co.redkiteweb.dccweb.services;

import com.google.common.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.Cab;
import uk.co.redkiteweb.dccweb.data.CabFunction;
import uk.co.redkiteweb.dccweb.data.CabFunctionComparator;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.model.Loco;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ChangeSpeedMessage;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.UpdateFunctionsMessage;
import uk.co.redkiteweb.dccweb.events.CabChangeEvent;

import java.util.Set;
import java.util.TreeSet;

import static org.mockito.Mockito.*;

/**
 * Created by shawn on 20/09/16.
 */
@RunWith(JUnit4.class)
public class CabServiceTest {

    private CabService cabService;
    private DccInterface dccInterface;
    private EventBus eventBus;

    @Before
    public void setup() {
        dccInterface = mock(DccInterface.class);
        eventBus = mock(EventBus.class);
        cabService = new CabService();
        cabService.setDccInterface(dccInterface);
        cabService.setEventBus(eventBus);
    }

    @Test
    public void testNullLoco() {
        cabService.updateCab(new Cab());
        verify(dccInterface, never()).sendMessage(any(Message.class));
    }

    @Test
    public void testNullDecoder() {
        final Loco loco = new Loco();
        final Cab cab = new Cab();
        cab.setLoco(loco);
        cabService.updateCab(cab);
        verify(dccInterface, never()).sendMessage(any(Message.class));
        verify(eventBus, never()).post(any(CabChangeEvent.class));
    }

    @Test
    public void testChangeSpeedUp() {
        final Cab cab = getCab();
        cab.setDirection("UP");
        cabService.updateCab(cab);
        verify(dccInterface, times(1)).sendMessage(any(ChangeSpeedMessage.class));
        verify(eventBus, times(1)).post(any(CabChangeEvent.class));
    }

    @Test
    public void testChangeSpeedDown() {
        final Cab cab = getCab();
        cab.setDirection("DOWN");
        cabService.updateCab(cab);
        verify(dccInterface, times(1)).sendMessage(any(ChangeSpeedMessage.class));
        verify(eventBus, times(1)).post(any(CabChangeEvent.class));
    }

    @Test
    public void testChangeSpeedRStop() {
        final Cab cab = getCab();
        cab.setDirection("RSTOP");
        cab.setSpeed(0);
        cabService.updateCab(cab);
        verify(dccInterface, times(1)).sendMessage(any(ChangeSpeedMessage.class));
        verify(eventBus, times(1)).post(any(CabChangeEvent.class));
    }

    @Test
    public void testChangeSpeedFStop() {
        final Cab cab = getCab();
        cab.setDirection("FSTOP");
        cab.setSpeed(0);
        cabService.updateCab(cab);
        verify(dccInterface, times(1)).sendMessage(any(ChangeSpeedMessage.class));
        verify(eventBus, times(1)).post(any(CabChangeEvent.class));
    }


    @Test
    public void testChangeSpeed28Steps() {
        final Cab cab = getCab();
        cab.setSteps("28");
        cabService.updateCab(cab);
        verify(dccInterface, times(1)).sendMessage(any(ChangeSpeedMessage.class));
        verify(eventBus, times(1)).post(any(CabChangeEvent.class));
    }

    @Test
    public void testChangeSpeedNullSteps() {
        final Cab cab = getCab();
        cab.setSteps(null);
        cabService.updateCab(cab);
        verify(dccInterface, times(1)).sendMessage(any(ChangeSpeedMessage.class));
        verify(eventBus, times(1)).post(any(CabChangeEvent.class));
    }

    @Test
    public void testUpdateFunctionNullLoco() {
        cabService.updateCabFunctions(new Cab());
        verify(dccInterface, never()).sendMessage(any(Message.class));
        verify(eventBus, never()).post(any(CabChangeEvent.class));
    }

    @Test
    public void testUpdateFunctionLoco() {
        final Cab cab = getCab();
        cabService.updateCabFunctions(cab);
        verify(dccInterface, times(1)).sendMessage(any(UpdateFunctionsMessage.class));
        verify(eventBus, times(1)).post(any(CabChangeEvent.class));
    }

    private Cab getCab() {
        final Decoder decoder = new Decoder();
        decoder.setCurrentAddress(1234);
        decoder.setAddressMode(true);
        final Loco loco = new Loco();
        loco.setDecoder(decoder);
        final Cab cab = new Cab();
        cab.setSpeed(127);
        cab.setSteps("128");
        cab.setLoco(loco);
        final Set<CabFunction> cabFunctions = new TreeSet<>(new CabFunctionComparator());
        final CabFunction cabFunction = new CabFunction();
        cabFunction.setNumber(1);
        cabFunction.setState(true);
        cabFunctions.add(cabFunction);
        cab.setCabFunctions(cabFunctions);
        return cab;
    }

}
