package uk.co.redkiteweb.dccweb.data.store;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.LogEntry;
import uk.co.redkiteweb.dccweb.store.LogStore;

import java.util.ArrayList;
import java.util.List;

@Component
public class LogStoreImpl implements LogStore {

    private static final Logger LOGGER = LogManager.getLogger(LogStoreImpl.class);

    private final List<LogEntry> logEntryStore;
    private SimpMessagingTemplate messagingTemplate;

    public LogStoreImpl() {
        logEntryStore = new ArrayList<>();
    }

    @Autowired
    public void setMessagingTemplate(final SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void log(final String level, final String message) {
        LOGGER.info(message);
        final LogEntry logEntry = new LogEntry();
        logEntry.setLevel(level);
        logEntry.setMessage(message);
        messagingTemplate.convertAndSend("/logging", logEntry);
        logEntryStore.add(logEntry);
    }

    @Override
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
