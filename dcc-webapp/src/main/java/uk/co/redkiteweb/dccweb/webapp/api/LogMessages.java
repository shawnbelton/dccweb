package uk.co.redkiteweb.dccweb.webapp.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uk.co.redkiteweb.dccweb.data.LogEntry;
import uk.co.redkiteweb.dccweb.data.store.LogStore;

import java.util.List;

/**
 * Created by shawn on 14/09/16.
 */
@RestController
public class LogMessages {

    private static final Logger LOGGER = LogManager.getLogger(LogMessages.class);

    private LogStore logStore;

    @Autowired
    public void setLogStore(final LogStore logStore) {
        this.logStore = logStore;
    }

    @RequestMapping(value = "/api/messages", method = RequestMethod.GET)
    public @ResponseBody List<LogEntry> getMessages() {
        LOGGER.info("Log Messages Requested");
        return logStore.getLastSix();
    }

}
