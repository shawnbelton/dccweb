package uk.co.redkiteweb.dccweb.webapp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uk.co.redkiteweb.dccweb.data.LogEntry;
import uk.co.redkiteweb.dccweb.store.LogStore;

import java.util.List;

/**
 * Created by shawn on 14/09/16.
 */
@RestController
public class LogMessages {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogMessages.class);

    private LogStore logStore;

    @Autowired
    public void setLogStore(final LogStore logStore) {
        this.logStore = logStore;
    }

    @GetMapping(value = "/api/messages")
    public @ResponseBody List<LogEntry> getMessages() {
        LOGGER.info("Log Messages Requested");
        return logStore.getLastSix();
    }

}
