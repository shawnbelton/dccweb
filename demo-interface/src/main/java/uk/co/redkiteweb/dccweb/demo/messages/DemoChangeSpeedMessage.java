package uk.co.redkiteweb.dccweb.demo.messages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ChangeSpeedMessage;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;

/**
 * Created by shawn on 21/09/16.
 */
@Component("DemoChangeSpeedMessage")
public class DemoChangeSpeedMessage extends AbstractDemoMessage implements DemoMessage {

    private static final Logger LOGGER = LogManager.getLogger(DemoChangeSpeedMessage.class);

    @Override
    public MessageResponse process(final Message message) {
        final ChangeSpeedMessage changeSpeedMessage = (ChangeSpeedMessage)message;
        LOGGER.info(String.format("Speed changed to %s %d", changeSpeedMessage.getDirection(), changeSpeedMessage.getSpeed()));
        return getMessageResponse();
    }
}
