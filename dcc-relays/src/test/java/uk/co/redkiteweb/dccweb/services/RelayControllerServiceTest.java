package uk.co.redkiteweb.dccweb.services;

import com.google.common.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.RelayController;
import uk.co.redkiteweb.dccweb.data.repositories.RelayControllerRepository;
import uk.co.redkiteweb.dccweb.events.RelayUpdateEvent;
import uk.co.redkiteweb.dccweb.store.LogStore;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 31/05/17.
 */
@RunWith(JUnit4.class)
public class RelayControllerServiceTest {

    private RelayControllerService relayControllerService;
    private RelayControllerRepository relayControllerRepository;
    private EventBus eventBus;

    @Before
    public void setup() {
        final LogStore logStore = mock(LogStore.class);
        relayControllerRepository = mock(RelayControllerRepository.class);
        eventBus = mock(EventBus.class);
        relayControllerService = new RelayControllerService();
        relayControllerService.setRelayControllerRepository(relayControllerRepository);
        relayControllerService.setLogStore(logStore);
        relayControllerService.setEventBus(eventBus);
        when(relayControllerRepository.findAll()).thenReturn(new ArrayList<>());
    }

    @Test
    public void updateNewControllerTest() {
        final RelayController relayController = new RelayController();
        relayController.setIpAddress("192.168.1.1");
        relayController.setControllerId("ControllerId");
        assertNotNull(relayControllerService.updateController(relayController));
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
        assertNotNull(relayControllerService.updateController(relayController));
        verify(relayControllerRepository, times(1)).save(any(RelayController.class));
    }

    @Test
    public void allControllersTest() {
        assertNotNull(relayControllerService.getAllControllers());
    }

    @Test
    public void saveTest() {
        assertNotNull(relayControllerService.save(mock(RelayController.class)));
    }

    @Test
    public void setRelayNoExist() {
        relayControllerService.setRelay("ABCDEFGH",3);
        verify(relayControllerRepository, never()).save(any(RelayController.class));
    }

    @Test
    public void setRelayTest() {
        final RelayController relayController = mock(RelayController.class);
        when(relayControllerRepository.findOne(anyString())).thenReturn(relayController);
        when(relayController.getValue()).thenReturn(2);
        relayControllerService.setRelay("ABCDEFGH",3);
        verify(relayController, times(1)).setValue(eq(6));
        verify(relayControllerRepository, times(1)).save(any(RelayController.class));
        verify(eventBus, times(1)).post(any(RelayUpdateEvent.class));
    }

    @Test
    public void unsetRelayNoExist() {
        relayControllerService.unsetRelay("ABCDEFGH",3);
        verify(relayControllerRepository, never()).save(any(RelayController.class));
    }

    @Test
    public void unsetRelayTest() {
        final RelayController relayController = mock(RelayController.class);
        when(relayControllerRepository.findOne(anyString())).thenReturn(relayController);
        when(relayController.getValue()).thenReturn(10);
        relayControllerService.unsetRelay("ABCDEFGH",4);
        verify(relayController, times(1)).setValue(eq(2));
        verify(relayControllerRepository, times(1)).save(any(RelayController.class));
        verify(eventBus, times(1)).post(any(RelayUpdateEvent.class));
    }

    @Test
    public void updateValueTest() {
        final RelayController relayController = mock(RelayController.class);
        when(relayControllerRepository.findOne(anyString())).thenReturn(relayController);
        relayControllerService.updateValue(mock(RelayController.class));
        verify(relayControllerRepository, times(1)).save(any(RelayController.class));
        verify(eventBus, times(1)).post(any(RelayUpdateEvent.class));
    }
}
