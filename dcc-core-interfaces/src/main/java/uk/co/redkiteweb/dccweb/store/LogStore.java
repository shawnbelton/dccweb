package uk.co.redkiteweb.dccweb.store;

import uk.co.redkiteweb.dccweb.data.LogEntry;

import java.util.List;

public interface LogStore {

    void log(final String level, final String message);
    List<LogEntry> getLastSix();
}
