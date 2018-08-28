package uk.co.redkiteweb.dccweb.services;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import uk.co.redkiteweb.dccweb.events.SendEvent;

@Service
public class NotificationService {

    private static final Logger LOGGER = LogManager.getLogger(NotificationService.class);

    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    public void setEventBus(final EventBus eventBus) {
        eventBus.register(this);
    }

    @Autowired
    public void setMessagingTemplate(final SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Subscribe public void sendEventListener(final SendEvent sendEvent) {
        LOGGER.info(String.format("Sending to %s", sendEvent.getUrl()));
        messagingTemplate.convertAndSend(sendEvent.getUrl(), sendEvent.sendObject());
    }
}
