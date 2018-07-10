package uk.co.redkiteweb.dccweb.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.web.client.RestTemplate;
import uk.co.redkiteweb.dccweb.events.RelayUpdateEvent;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 01/06/17.
 */
@RunWith(JUnit4.class)
public class AsyncWebServiceTest {

    @Test
    public void webCallTest() {
        final RestTemplate restTemplate = mock(RestTemplate.class);
        final AsyncWebService asyncWebService = new AsyncWebService();
        asyncWebService.setRestTemplate(restTemplate);
        final RelayUpdateEvent event = mock(RelayUpdateEvent.class);
        when(event.getUpdateUrl()).thenReturn("URL");
        asyncWebService.relayUpdateListener(event);
        verify(restTemplate, times(1)).put(anyString(), eq(null));
    }

}
