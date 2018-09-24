package uk.co.redkiteweb.dccweb.webapp.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.RelayController;
import uk.co.redkiteweb.dccweb.services.RelayControllerService;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
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
        when(relayControllerService.updateController(any(RelayController.class))).thenReturn(mock(RelayController.class));
        assertNotNull(relayControllers.updateController(mock(RelayController.class)));
    }

    @Test
    public void testGetAllControllers() {
        when(relayControllerService.getAllControllers()).thenReturn(new ArrayList<>());
        assertNotNull(relayControllers.getAllControllers());
    }

    @Test
    public void testSave() {
        when(relayControllerService.save(any(RelayController.class))).thenReturn(new ArrayList<>());
        assertNotNull(relayControllers.save(mock(RelayController.class)));
    }

    @Test
    public void testUpdateValue() {
        when(relayControllerService.updateValue(any(RelayController.class))).thenReturn(new ArrayList<>());
        assertNotNull(relayControllers.updateValue(mock(RelayController.class)));
    }
}
