package uk.co.redkiteweb.dccweb.demo.messages;

import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;

/**
 * Created by shawn on 25/07/16.
 */
public abstract class AbstractDemoMessage implements DemoMessage {

    MessageResponse getMessageResponse() {
        final MessageResponse messageResponse = new MessageResponse();
        messageResponse.setStatus(MessageResponse.MessageStatus.OK);
        return messageResponse;
    }

    @Override
    public abstract MessageResponse process(final Message message);

}
