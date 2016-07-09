package uk.co.redkiteweb.dccweb.dccinterface.factories;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.MessageProcessor;

/**
 * Created by shawn on 07/07/16.
 */
@Component
public class MessageProcessorFactory implements ApplicationContextAware {

    private String interfaceId;

    private ApplicationContext context;

    @Value("${interfaceId}")
    public void setInterfaceId(final String interfaceId) {
        this.interfaceId = interfaceId;
    }

    @Override
    public void setApplicationContext(final ApplicationContext context) throws BeansException {
        this.context = context;
    }

    public MessageProcessor getInstance() {
        return context.getBean(interfaceId, MessageProcessor.class);
    }

}
