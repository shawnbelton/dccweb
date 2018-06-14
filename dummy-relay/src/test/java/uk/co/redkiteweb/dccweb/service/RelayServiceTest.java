package uk.co.redkiteweb.dccweb.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.context.embedded.EmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import uk.co.redkiteweb.dccweb.model.RegisterDetails;
import uk.co.redkiteweb.dccweb.model.RelayController;
import uk.co.redkiteweb.dccweb.store.RelayControllerStore;

import java.net.UnknownHostException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class RelayServiceTest {

    private RelayService relayService;
    private RestTemplate restTemplate;

    @Before
    public void setup() {
        final RelayControllerStore relayControllerStore = mock(RelayControllerStore.class);
        restTemplate = mock(RestTemplate.class);
        relayService = new RelayService();
        relayService.setRestTemplate(restTemplate);
        relayService.setRelayControllerStore(relayControllerStore);
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
    }

    @Test
    public void testRegisterUnknownHost() {
        final RegisterDetails details = new RegisterDetails();
        details.setHost("localhost");
        details.setPort(8080);
        when(restTemplate.postForEntity(anyString(), any(RelayController.class), eq(Integer.class))).thenThrow(UnknownHostException.class);
        relayService.registerWithServer(details);
    }

    @Test
    public void testGetPort() {
        final EmbeddedServletContainerInitializedEvent event = mock(EmbeddedServletContainerInitializedEvent.class);
        final EmbeddedServletContainer container = mock(EmbeddedServletContainer.class);
        when(event.getEmbeddedServletContainer()).thenReturn(container);
        when(container.getPort()).thenReturn(8090);
        relayService.onApplicationEvent(event);
    }
}
