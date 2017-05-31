package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.RelayController;
import uk.co.redkiteweb.dccweb.services.RelayControllerService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 31/05/17.
 */
@RunWith(JUnit4.class)
public class RelayControllersTest {

    private RelayControllers relayControllers;
    private RelayControllerService relayControllerService;

    @Before
    public void setup() {
        relayControllerService = mock(RelayControllerService.class);
        relayControllers = new RelayControllers();
        relayControllers.setRelayControllerService(relayControllerService);
    }

    @Test
    public void testUpdateController() {
        when(relayControllerService.updateController(any(RelayController.class))).thenReturn(12);
        assertEquals(new Integer(12), relayControllers.updateController(mock(RelayController.class)));
    }
}
