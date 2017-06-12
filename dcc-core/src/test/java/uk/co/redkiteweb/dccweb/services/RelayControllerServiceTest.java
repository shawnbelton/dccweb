package uk.co.redkiteweb.dccweb.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.RelayController;
import uk.co.redkiteweb.dccweb.data.repositories.RelayControllerRepository;
import uk.co.redkiteweb.dccweb.data.service.NotificationService;
import uk.co.redkiteweb.dccweb.data.store.LogStore;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
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
    private NotificationService notificationService;
    private AsyncWebService asyncWebService;

    @Before
    public void setup() {
        final LogStore logStore = mock(LogStore.class);
        notificationService = mock(NotificationService.class);
        asyncWebService = mock(AsyncWebService.class);
        relayControllerRepository = mock(RelayControllerRepository.class);
        relayControllerService = new RelayControllerService();
        relayControllerService.setRelayControllerRepository(relayControllerRepository);
        relayControllerService.setLogStore(logStore);
        relayControllerService.setNotificationService(notificationService);
        relayControllerService.setAsyncWebService(asyncWebService);
        when(relayControllerRepository.findAll()).thenReturn(new ArrayList<RelayController>());
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
        verify(notificationService, times(1)).createNotification(eq("RELAY"),eq(""));
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
        verify(notificationService, times(1)).createNotification(eq("RELAY"),eq(""));
    }

    @Test
    public void updateValueTest() {
        final RelayController relayController = mock(RelayController.class);
        when(relayControllerRepository.findOne(anyString())).thenReturn(relayController);
        relayControllerService.updateValue(mock(RelayController.class));
        verify(relayControllerRepository, times(1)).save(any(RelayController.class));
        verify(notificationService, times(1)).createNotification(anyString(),anyString());
    }
}
