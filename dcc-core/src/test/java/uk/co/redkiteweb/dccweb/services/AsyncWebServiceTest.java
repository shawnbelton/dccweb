package uk.co.redkiteweb.dccweb.services;

import com.google.common.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import uk.co.redkiteweb.dccweb.data.store.LogStore;
import uk.co.redkiteweb.dccweb.events.RelayUpdateEvent;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 01/06/17.
 */
@RunWith(JUnit4.class)
public class AsyncWebServiceTest {

    private AsyncWebService asyncWebService;
    private RestTemplate restTemplate;
    private LogStore logStore;

    @Before
    public void setup() {
        restTemplate = mock(RestTemplate.class);
        logStore = mock(LogStore.class);
        asyncWebService = new AsyncWebService();
        asyncWebService.setEventBus(mock(EventBus.class));
        asyncWebService.setRestTemplate(restTemplate);
        asyncWebService.setLogStore(logStore);
    }

    @Test
    public void webCallTest() {
        final RelayUpdateEvent event = mock(RelayUpdateEvent.class);
        when(event.getUpdateUrl()).thenReturn("URL");
        asyncWebService.relayUpdateListener(event);
        verify(restTemplate, times(1)).put(anyString(), eq(null));
    }

    @Test
    public void testWebCallFail() {
        final RelayUpdateEvent event = mock(RelayUpdateEvent.class);
        when(event.getUpdateUrl()).thenReturn("URL");
        doThrow(mock(HttpClientErrorException.class)).when(restTemplate).put(anyString(), eq(null));
        asyncWebService.relayUpdateListener(event);
        verify(logStore, times(1)).log(eq("ERROR"), anyString());
    }
}
