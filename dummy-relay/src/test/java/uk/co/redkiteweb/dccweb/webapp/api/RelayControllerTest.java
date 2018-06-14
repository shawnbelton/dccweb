package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.model.RegisterDetails;
import uk.co.redkiteweb.dccweb.service.RelayService;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(JUnit4.class)
public class RelayControllerTest {

    private RelayController relayController;
    private RelayService relayService;

    @Before
    public void setup() {
        relayService = mock(RelayService.class);
        relayController = new RelayController();
        relayController.setRelayService(relayService);
    }

    @Test
    public void testRegister() {
        relayController.registerController(mock(RegisterDetails.class));
        verify(relayService, times(1)).registerWithServer(any(RegisterDetails.class));
    }

}
