package uk.co.redkiteweb.dccweb.webapp.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Decoder;
import uk.co.redkiteweb.dccweb.data.model.Train;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ChangeSpeedMessage;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.UpdateFunctionsMessage;
import uk.co.redkiteweb.dccweb.webapp.data.Cab;
import uk.co.redkiteweb.dccweb.webapp.data.CabFunction;
import uk.co.redkiteweb.dccweb.webapp.data.CabFunctionComparator;

import java.util.Set;
import java.util.TreeSet;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 20/09/16.
 */
@RunWith(JUnit4.class)
public class CabServiceTest {

    private CabService cabService;
    private DccInterface dccInterface;

    @Before
    public void setup() {
        dccInterface = mock(DccInterface.class);
        cabService = new CabService();
        cabService.setDccInterface(dccInterface);
    }

    @Test
    public void testNullTrain() {
        cabService.updateCab(new Cab());
        verify(dccInterface, never()).sendMessage(any(Message.class));
    }

    @Test
    public void testNullDecoder() {
        final Train train = new Train();
        final Cab cab = new Cab();
        cab.setTrain(train);
        cabService.updateCab(cab);
        verify(dccInterface, never()).sendMessage(any(Message.class));
    }

    @Test
    public void testChangeSpeedUp() {
        final Cab cab = getCab();
        cab.setDirection("UP");
        cabService.updateCab(cab);
        verify(dccInterface, times(1)).sendMessage(any(ChangeSpeedMessage.class));
    }

    @Test
    public void testChangeSpeedDown() {
        final Cab cab = getCab();
        cab.setDirection("DOWN");
        cabService.updateCab(cab);
        verify(dccInterface, times(1)).sendMessage(any(ChangeSpeedMessage.class));
    }

    @Test
    public void testChangeSpeedRStop() {
        final Cab cab = getCab();
        cab.setDirection("RSTOP");
        cab.setSpeed(0);
        cabService.updateCab(cab);
        verify(dccInterface, times(1)).sendMessage(any(ChangeSpeedMessage.class));
    }

    @Test
    public void testChangeSpeedFStop() {
        final Cab cab = getCab();
        cab.setDirection("FSTOP");
        cab.setSpeed(0);
        cabService.updateCab(cab);
        verify(dccInterface, times(1)).sendMessage(any(ChangeSpeedMessage.class));
    }


    @Test
    public void testChangeSpeed28Steps() {
        final Cab cab = getCab();
        cab.setSteps("28");
        cabService.updateCab(cab);
        verify(dccInterface, times(1)).sendMessage(any(ChangeSpeedMessage.class));
    }

    @Test
    public void testChangeSpeedNullSteps() {
        final Cab cab = getCab();
        cab.setSteps(null);
        cabService.updateCab(cab);
        verify(dccInterface, times(1)).sendMessage(any(ChangeSpeedMessage.class));
    }

    @Test
    public void testUpdateFunctionNullTrain() {
        cabService.updateCabFunctions(new Cab());
        verify(dccInterface, never()).sendMessage(any(Message.class));
    }

    @Test
    public void testUpdateFunctionTrain() {
        final Cab cab = getCab();
        cabService.updateCabFunctions(cab);
        verify(dccInterface, times(1)).sendMessage(any(UpdateFunctionsMessage.class));
    }

    private Cab getCab() {
        final Decoder decoder = new Decoder();
        decoder.setCurrentAddress(1234);
        decoder.setAddressMode(true);
        final Train train = new Train();
        train.setDecoder(decoder);
        final Cab cab = new Cab();
        cab.setSpeed(127);
        cab.setSteps("128");
        cab.setTrain(train);
        final Set<CabFunction> cabFunctions = new TreeSet<CabFunction>(new CabFunctionComparator());
        final CabFunction cabFunction = new CabFunction();
        cabFunction.setNumber(1);
        cabFunction.setState(true);
        cabFunctions.add(cabFunction);
        cab.setCabFunctions(cabFunctions);
        return cab;
    }

}
