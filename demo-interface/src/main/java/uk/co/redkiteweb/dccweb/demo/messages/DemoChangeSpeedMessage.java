package uk.co.redkiteweb.dccweb.demo.messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ChangeSpeedMessage;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;

/**
 * Created by shawn on 21/09/16.
 */
@Component("DemoChangeSpeedMessage")
public class DemoChangeSpeedMessage implements DefaultResponseDemoMessage, DemoMessage {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoChangeSpeedMessage.class);

    @Override
    public MessageResponse process(final Message message) {
        final ChangeSpeedMessage changeSpeedMessage = (ChangeSpeedMessage)message;
        LOGGER.info(String.format("Speed changed to %s %d", changeSpeedMessage.getDirection(), changeSpeedMessage.getSpeed()));
        return getMessageResponse();
    }
}
