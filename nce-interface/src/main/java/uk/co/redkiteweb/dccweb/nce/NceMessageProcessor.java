package uk.co.redkiteweb.dccweb.nce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.factories.MessageProcessor;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;
import uk.co.redkiteweb.dccweb.nce.messages.NceMessage;

/**
 * Created by shawn on 15/06/16.
 */
@Component("Nce")
public class NceMessageProcessor implements MessageProcessor, ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(NceMessageProcessor.class);

    private ApplicationContext applicationContext;

    @Override
    public String getInterfaceCode() {
        return "Nce";
    }

    @Override
    public String getInterfaceName() {
        return "NCE DCC System";
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public MessageResponse process(final Message message) {
        MessageResponse messageResponse = new MessageResponse();
        final NceMessage nceMessage = applicationContext.getBean("Nce" + message.getClass().getSimpleName(), NceMessage.class);
        try {
            messageResponse = nceMessage.process(message);
        } catch (ConnectionException exception) {
            LOGGER.warn(exception.getMessage(), exception);
            messageResponse.setStatus(MessageResponse.MessageStatus.ERROR);
            messageResponse.put("ERROR", "Disconnected");
        }
        return messageResponse;
    }

}
