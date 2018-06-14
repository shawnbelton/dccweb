package uk.co.redkiteweb.dccweb.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by shawn on 01/06/17.
 */
@Service
public class AsyncWebService {

    private static final Logger LOGGER = LogManager.getLogger(AsyncWebService.class);

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    public void asyncWebCall(final String url) {
        LOGGER.info("Putting data to url: {}", url);
        restTemplate.put(url, null);
    }

}
