package uk.co.redkiteweb.dccweb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.redkiteweb.dccweb.model.RegisterDetails;
import uk.co.redkiteweb.dccweb.model.RelayController;
import uk.co.redkiteweb.dccweb.store.RelayControllerStore;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class RelayService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RelayService.class);

    private RestTemplate restTemplate;
    private RelayControllerStore relayControllerStore;
    private Environment environment;

    @Autowired
    public void setEnvironment(final Environment environment) {
        this.environment = environment;

    }

    @Autowired
    public void setRelayControllerStore(final RelayControllerStore relayControllerStore) {
        this.relayControllerStore = relayControllerStore;
    }

    @Autowired
    public void setRestTemplate(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void registerWithServer(final RegisterDetails registerDetails) {
        final String url = String.format("http://%s:%d/api/relay-controller/update",
                registerDetails.getHost(), registerDetails.getPort());
        try {
            final String host = InetAddress.getLocalHost().getHostAddress();
            final RelayController relayController = relayControllerStore.getRelayController();
            relayController.setControllerId("DUMMY");
            relayController.setIpAddress(String.format("%s:%s", host, environment.getProperty("local.server.port")));
            relayControllerStore.setRelayController(relayController);
            relayControllerStore.setValue(restTemplate.postForEntity(url, relayController, Integer.class).getBody());
        } catch (final UnknownHostException exception) {
            LOGGER.warn("Unknown host: {}", exception.getMessage());
        }
    }

}
