package uk.co.redkiteweb.dccweb.webapp.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;

/**
 * Created by shawn on 18/06/16.
 */
@Component
public class ShutdownListener {

    private DccInterface dccInterface;

    @Autowired
    public void setDccInterface(final DccInterface dccInterface) {
        this.dccInterface = dccInterface;
    }

    @EventListener(ContextClosedEvent.class)
    public void onApplicationEvent() {
        dccInterface.shutdown();
    }
}
