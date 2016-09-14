package uk.co.redkiteweb.dccweb.data.store;

import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.data.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shawn on 14/09/16.
 */
@Component
public class LogStore {

    private List<Log> logStore;

    public LogStore() {
        logStore = new ArrayList<Log>();
    }

    public void log(final String level, final String message) {
        final Log log = new Log();
        log.setLevel(level);
        log.setMessage(message);
        logStore.add(log);
    }

    public List<Log> getLastSix() {
        final List<Log> lastSix = new ArrayList<Log>();
        for(int index = logStore.size()-1; index > logStore.size()-7; index--) {
            if (index>=0) {
                lastSix.add(logStore.get(index));
            }
        }
        return lastSix;
    }
}
