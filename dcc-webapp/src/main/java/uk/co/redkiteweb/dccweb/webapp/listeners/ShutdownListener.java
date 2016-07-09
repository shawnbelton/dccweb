package uk.co.redkiteweb.dccweb.webapp.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;

/**
 * Created by shawn on 18/06/16.
 */
@Component
public class ShutdownListener implements ApplicationListener<ContextClosedEvent> {

    private DccInterface dccInterface;

    @Autowired
    public void setDccInterface(final DccInterface dccInterface) {
        this.dccInterface = dccInterface;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        dccInterface.shutdown();
    }
}
