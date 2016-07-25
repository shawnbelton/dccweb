package uk.co.redkiteweb.dccweb.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.factories.MessageProcessor;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.demo.messages.DemoMessage;

/**
 * Created by shawn on 25/07/16.
 */
@Component("Demo")
public class DemoMessageProcessor implements MessageProcessor, ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) {
        this.context = applicationContext;
    }

    @Override
    public MessageResponse process(Message message) {
        final DemoMessage demoMessage = context.getBean("Demo" + message.getClass().getSimpleName(), DemoMessage.class);
        return demoMessage.process(message);
    }
}
