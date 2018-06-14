package uk.co.redkiteweb.dccweb.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.redkiteweb.dccweb.model.RegisterDetails;
import uk.co.redkiteweb.dccweb.model.RelayController;
import uk.co.redkiteweb.dccweb.store.RelayControllerStore;

import java.net.Inet4Address;
import java.net.UnknownHostException;

@Service
public class RelayService implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    private static final Logger LOGGER = LogManager.getLogger(RelayService.class);

    private RestTemplate restTemplate;
    private Integer port;
    private RelayControllerStore relayControllerStore;

    @Autowired
    public void setRelayControllerStore(final RelayControllerStore relayControllerStore) {
        this.relayControllerStore = relayControllerStore;
    }

    @Autowired
    public void setRestTemplate(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void onApplicationEvent(final EmbeddedServletContainerInitializedEvent embeddedServletContainerInitializedEvent) {
        port = embeddedServletContainerInitializedEvent.getEmbeddedServletContainer().getPort();
    }

    public void registerWithServer(final RegisterDetails registerDetails) {
        final String url = String.format("http://%s:%d/api/relay-controller/update",
                registerDetails.getHost(), registerDetails.getPort());
        try {
            final String host = Inet4Address.getLocalHost().getHostAddress();
            final RelayController relayController = relayControllerStore.getRelayController();
            relayController.setControllerId("DUMMY");
            relayController.setIpAddress(String.format("%s:%d", host, port));
            relayControllerStore.setRelayController(relayController);
            relayControllerStore.setValue(restTemplate.postForEntity(url, relayController, Integer.class).getBody());
        } catch (final UnknownHostException exception) {
            LOGGER.warn("Unknown host: {}", exception.getMessage());
        }
    }

}
