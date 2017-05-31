package uk.co.redkiteweb.dccweb.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.RelayController;
import uk.co.redkiteweb.dccweb.data.repositories.RelayControllerRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 31/05/17.
 */
@RunWith(JUnit4.class)
public class RelayControllerServiceTest {

    private RelayControllerService relayControllerService;
    private RelayControllerRepository relayControllerRepository;

    @Before
    public void setup() {
        relayControllerRepository = mock(RelayControllerRepository.class);
        relayControllerService = new RelayControllerService();
        relayControllerService.setRelayControllerRepository(relayControllerRepository);
    }

    @Test
    public void updateNewControllerTest() {
        final RelayController relayController = new RelayController();
        relayController.setIpAddress("192.168.1.1");
        relayController.setControllerId("ControllerId");
        assertEquals(new Integer(0), relayControllerService.updateController(relayController));
        verify(relayControllerRepository, times(1)).save(any(RelayController.class));
    }

    @Test
    public void updateExistingControllerTest() {
        final RelayController existingController = mock(RelayController.class);
        when(existingController.getValue()).thenReturn(10);
        when(relayControllerRepository.findOne(anyString())).thenReturn(existingController);
        final RelayController relayController = new RelayController();
        relayController.setIpAddress("192.168.1.1");
        relayController.setControllerId("ControllerId");
        assertEquals(new Integer(10), relayControllerService.updateController(relayController));
        verify(relayControllerRepository, times(1)).save(any(RelayController.class));
    }

}
