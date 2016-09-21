package uk.co.redkiteweb.dccweb.webapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uk.co.redkiteweb.dccweb.data.data.LogEntry;
import uk.co.redkiteweb.dccweb.data.store.LogStore;

import java.util.List;

/**
 * Created by shawn on 14/09/16.
 */
@RestController
public class LogMessages {

    private LogStore logStore;

    @Autowired
    public void setLogStore(final LogStore logStore) {
        this.logStore = logStore;
    }

    @RequestMapping("/messages")
    public @ResponseBody List<LogEntry> getMessages() {
        return logStore.getLastSix();
    }

}