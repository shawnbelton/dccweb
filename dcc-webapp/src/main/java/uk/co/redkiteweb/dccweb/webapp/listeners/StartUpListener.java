package uk.co.redkiteweb.dccweb.webapp.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.loaders.DccManufacturerLoader;
import uk.co.redkiteweb.dccweb.data.loaders.Loader;
import uk.co.redkiteweb.dccweb.factories.DccInterfaceFactory;

/**
 * Created by shawn on 14/06/16.
 */
@Component
public class StartUpListener implements ApplicationListener<ContextRefreshedEvent> {

    private DccInterfaceFactory dccInterfaceFactory;
    private Loader loader;

    @Autowired
    public void setDccInterfaceFactory(final DccInterfaceFactory dccInterfaceFactory) {
        this.dccInterfaceFactory = dccInterfaceFactory;
    }

    @Autowired
    @Qualifier("DccManufacturer")
    public void setDccManufacturerLoader(final Loader loader) {
        this.loader = loader;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
        loader.load();
        dccInterfaceFactory.getInstance().initialise();
    }
}
