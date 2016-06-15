package uk.co.redkiteweb.dccweb.webapp.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.factories.DccInterfaceFactory;

/**
 * Created by shawn on 14/06/16.
 */
@Component
public class StartUpListener implements ApplicationListener<ContextRefreshedEvent> {

    private DccInterfaceFactory dccInterfaceFactory;

    @Autowired
    public void setDccInterfaceFactory(final DccInterfaceFactory dccInterfaceFactory) {
        this.dccInterfaceFactory = dccInterfaceFactory;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
        dccInterfaceFactory.getInstance().initialise();
    }
}
