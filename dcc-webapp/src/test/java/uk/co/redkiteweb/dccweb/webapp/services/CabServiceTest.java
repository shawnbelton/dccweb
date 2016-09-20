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
import uk.co.redkiteweb.dccweb.webapp.data.Cab;

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
    public void testChangeSpeedStop() {

        final Cab cab = getCab();
        cab.setDirection("STOP");
        cab.setSpeed(0);
        cabService.updateCab(cab);
        verify(dccInterface, times(1)).
        sendMessage(any(ChangeSpeedMessage.class));
    }

    private Cab getCab() {
        final Decoder decoder = new Decoder();
        decoder.setCurrentAddress(1234);
        final Train train = new Train();
        train.setDecoder(decoder);
        final Cab cab = new Cab();
        cab.setSpeed(127);
        cab.setTrain(train);
        return cab;
    }

}
