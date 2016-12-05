package uk.co.redkiteweb.dccweb.demo.messages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.dccinterface.messages.UpdateFunctionsMessage;

/**
 * Created by shawn on 21/09/16.
 */
@Component("DemoUpdateFunctionsMessage")
public class DemoUpdateFunctionsMessage extends AbstractDemoMessage implements DemoMessage {

    private static final Logger LOGGER = LogManager.getLogger(DemoUpdateFunctionsMessage.class);

    @Override
    public MessageResponse process(Message message) {
        final UpdateFunctionsMessage updateFunctionsMessage = (UpdateFunctionsMessage)message;
        LOGGER.info(String.format("Setting Functions on %d", updateFunctionsMessage.getAddress()));
        return getMessageResponse();
    }
}
