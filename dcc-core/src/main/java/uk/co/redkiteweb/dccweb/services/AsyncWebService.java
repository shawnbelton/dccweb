package uk.co.redkiteweb.dccweb.services;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import uk.co.redkiteweb.dccweb.data.store.LogStore;
import uk.co.redkiteweb.dccweb.events.RelayUpdateEvent;

/**
 * Created by shawn on 01/06/17.
 */
@Service
public class AsyncWebService {

    private static final Logger LOGGER = LogManager.getLogger(AsyncWebService.class);

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
