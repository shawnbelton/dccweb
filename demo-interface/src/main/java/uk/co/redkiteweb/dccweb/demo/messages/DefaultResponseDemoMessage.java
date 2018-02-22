package uk.co.redkiteweb.dccweb.demo.messages;

import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;

/**
 * Created by shawn on 25/07/16.
 */
public interface DefaultResponseDemoMessage extends DemoMessage {

    default MessageResponse getMessageResponse() {
        final MessageResponse messageResponse = new MessageResponse();
        messageResponse.setStatus(MessageResponse.MessageStatus.OK);
        return messageResponse;
    }
}
