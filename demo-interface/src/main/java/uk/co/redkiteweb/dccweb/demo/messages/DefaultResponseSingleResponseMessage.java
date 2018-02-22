package uk.co.redkiteweb.dccweb.demo.messages;

import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;

/**
 * Created by shawn on 25/07/16.
 */
public interface DefaultResponseSingleResponseMessage extends DefaultResponseDemoMessage {

    default MessageResponse singleResponse(final String key, final Object value) {
        final MessageResponse messageResponse = getMessageResponse();
        messageResponse.put(key, value);
        return messageResponse;
    }
}
