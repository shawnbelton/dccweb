package uk.co.redkiteweb.dccweb.webapp.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.loaders.Loader;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;

/**
 * Created by shawn on 14/06/16.
 */
@Component
public class StartUpListener implements ApplicationListener<ContextRefreshedEvent> {

    private DccInterface dccInterface;
    private Loader loader;

    @Autowired
    public void setDccInterface(final DccInterface dccInterface) {
        this.dccInterface = dccInterface;
    }

    @Autowired
    @Qualifier("DccManufacturer")
    public void setDccManufacturerLoader(final Loader loader) {
        this.loader = loader;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
        loader.load();
        dccInterface.initialise();
    }
}
