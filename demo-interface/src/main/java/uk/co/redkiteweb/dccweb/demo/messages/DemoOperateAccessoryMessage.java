package uk.co.redkiteweb.dccweb.demo.messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.dccinterface.messages.OperateAccessoryMessage;

/**
 * Created by shawn on 06/03/17.
 */
@Component("DemoOperateAccessoryMessage")
public class DemoOperateAccessoryMessage implements DefaultResponseDemoMessage, DemoMessage {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoOperateAccessoryMessage.class);

    @Override
    public MessageResponse process(final Message message) {
        final OperateAccessoryMessage changeSpeedMessage = (OperateAccessoryMessage)message;
        LOGGER.info(String.format("Accessory %d changed too %d",
                changeSpeedMessage.getAccessoryAddress(), changeSpeedMessage.getAccessoryOperation()));
        return getMessageResponse();
    }
}
