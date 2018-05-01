package uk.co.redkiteweb.dccweb.data.store;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.data.LogEntry;
import uk.co.redkiteweb.dccweb.data.service.NotificationService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shawn on 14/09/16.
 */
@Component
public class LogStore {

    private static final Logger LOGGER = LogManager.getLogger(LogStore.class);

    private final List<LogEntry> logEntryStore;
    private NotificationService notificationService;
    private SimpMessagingTemplate messagingTemplate;

    public LogStore() {
        logEntryStore = new ArrayList<>();
    }

    @Autowired
    public void setNotificationService(final NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Autowired
    public void setMessagingTemplate(final SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void log(final String level, final String message) {
        LOGGER.info(message);
        notificationService.createNotification("MESSAGES","");
        final LogEntry logEntry = new LogEntry();
        logEntry.setLevel(level);
        logEntry.setMessage(message);
        messagingTemplate.convertAndSend("/logging", logEntry);
        logEntryStore.add(logEntry);
    }

    public List<LogEntry> getLastSix() {
        final List<LogEntry> lastSix = new ArrayList<>();
        for(int index = logEntryStore.size()-1; index > logEntryStore.size()-7; index--) {
            if (index>=0) {
                lastSix.add(logEntryStore.get(index));
            }
        }
        return lastSix;
    }
}
