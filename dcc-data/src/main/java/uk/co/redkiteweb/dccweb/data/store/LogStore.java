package uk.co.redkiteweb.dccweb.data.store;

import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.data.LogEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shawn on 14/09/16.
 */
@Component
public class LogStore {

    private final List<LogEntry> logEntryStore;

    public LogStore() {
        logEntryStore = new ArrayList<LogEntry>();
    }

    public void log(final String level, final String message) {
        final LogEntry logEntry = new LogEntry();
        logEntry.setLevel(level);
        logEntry.setMessage(message);
        logEntryStore.add(logEntry);
    }

    public List<LogEntry> getLastSix() {
        final List<LogEntry> lastSix = new ArrayList<LogEntry>();
        for(int index = logEntryStore.size()-1; index > logEntryStore.size()-7; index--) {
            if (index>=0) {
                lastSix.add(logEntryStore.get(index));
            }
        }
        return lastSix;
    }
}
