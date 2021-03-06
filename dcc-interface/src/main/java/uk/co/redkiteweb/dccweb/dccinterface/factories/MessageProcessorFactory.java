package uk.co.redkiteweb.dccweb.dccinterface.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.service.SettingsService;

import java.util.Collection;

/**
 * Created by shawn on 07/07/16.
 */
@Component
public class MessageProcessorFactory implements ApplicationContextAware {

    private ApplicationContext context;
    private SettingsService settingsService;

    @Autowired
    public void setSettingsService(final SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @Override
    public void setApplicationContext(final ApplicationContext context) {
        this.context = context;
    }

    public MessageProcessor getInstance() {
        return context.getBean(settingsService.getSettingValue("InterfaceType", "Demo"), MessageProcessor.class);
    }

    public Collection<MessageProcessor> getAllInterfaces() {
        return context.getBeansOfType(MessageProcessor.class).values();
    }
}
