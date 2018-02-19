package uk.co.redkiteweb.dccweb.webapp.listeners;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.loaders.Loader;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;

import java.util.Map;

/**
 * Created by shawn on 14/06/16.
 */
@Component
public class StartUpListener implements ApplicationContextAware {

    private DccInterface dccInterface;
    private ApplicationContext applicationContext;

    @Autowired
    public void setDccInterface(final DccInterface dccInterface) {
        this.dccInterface = dccInterface;
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent() {
        final Map<String, Loader> loaders = applicationContext.getBeansOfType(Loader.class);
        for(Loader loader : loaders.values()) {
            loader.load();
        }
        dccInterface.initialise();
    }
}
