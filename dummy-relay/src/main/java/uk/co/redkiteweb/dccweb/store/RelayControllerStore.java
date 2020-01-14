package uk.co.redkiteweb.dccweb.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.model.RelayController;

@Component
public class RelayControllerStore {

    private static final Logger LOGGER = LoggerFactory.getLogger(RelayControllerStore.class);

    private RelayController relayController;
    private SimpMessagingTemplate messagingTemplate;

    public RelayControllerStore() {
        relayController = new RelayController();
    }

    @Autowired
    public void setMessagingTemplate(final SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public RelayController getRelayController() {
        return relayController;
    }

    public void setRelayController(final RelayController relayController) {
        this.relayController = relayController;
    }

    public void setValue(final Integer value) {
        LOGGER.info("Relay Value set to: {}", value);
        relayController.setValue(value);
        messagingTemplate.convertAndSend("/relays", value);
    }
}
