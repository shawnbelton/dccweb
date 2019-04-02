package uk.co.redkiteweb.dccweb.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import uk.co.redkiteweb.dccweb.model.RegisterDetails;
import uk.co.redkiteweb.dccweb.model.RelayController;
import uk.co.redkiteweb.dccweb.store.RelayControllerStore;

import java.net.UnknownHostException;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class RelayServiceTest {

    private RelayService relayService;
    private RestTemplate restTemplate;
    private RelayControllerStore relayControllerStore;

    @Before
    public void setup() {
        final Environment environment = mock(Environment.class);
        relayControllerStore = mock(RelayControllerStore.class);
        restTemplate = mock(RestTemplate.class);
        relayService = new RelayService();
        relayService.setRestTemplate(restTemplate);
        relayService.setRelayControllerStore(relayControllerStore);
        relayService.setEnvironment(environment);
        when(environment.getProperty(eq("local.server.port"))).thenReturn("8090");
        when(relayControllerStore.getRelayController()).thenReturn(mock(RelayController.class));
    }

    @Test
    public void testRegister() {
        final RegisterDetails details = new RegisterDetails();
        details.setHost("localhost");
        details.setPort(8080);
        final ResponseEntity<Integer> responseEntity = mock(ResponseEntity.class);
        when(restTemplate.postForEntity(anyString(), any(RelayController.class), eq(Integer.class))).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(10);
        relayService.registerWithServer(details);
        verify(relayControllerStore, times(1)).setValue(anyInt());
    }

    @Test
    public void testRegisterUnknownHost() {
        final RegisterDetails details = new RegisterDetails();
        details.setHost("localhost");
        details.setPort(8080);
        when(restTemplate.postForEntity(anyString(), any(RelayController.class), eq(Integer.class))).thenThrow(UnknownHostException.class);
        relayService.registerWithServer(details);
        verify(relayControllerStore, never()).setValue(anyInt());
    }

}
