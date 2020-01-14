package uk.co.redkiteweb.dccweb.services;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import uk.co.redkiteweb.dccweb.events.RelayUpdateEvent;
import uk.co.redkiteweb.dccweb.store.LogStore;

/**
 * Created by shawn on 01/06/17.
 */
@Service
public class AsyncWebService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncWebService.class);

    private RestTemplate restTemplate;
    private LogStore logStore;

    @Autowired
    public void setEventBus(final EventBus eventBus) {
        eventBus.register(this);
    }

    @Autowired
    public void setRestTemplate(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setLogStore(final LogStore logStore) {
        this.logStore = logStore;
    }

    @Subscribe public void relayUpdateListener(final RelayUpdateEvent event) {
        asyncWebCall(event.getUpdateUrl());
    }

    private void asyncWebCall(final String url) {
        LOGGER.info("Putting data to url: {}", url);
        try {
            restTemplate.put(url, null);
        } catch (final HttpClientErrorException exception) {
            logStore.log("ERROR", exception.getMessage());
        }
    }

}
