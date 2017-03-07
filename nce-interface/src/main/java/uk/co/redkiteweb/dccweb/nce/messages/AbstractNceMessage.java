package uk.co.redkiteweb.dccweb.nce.messages;

import org.springframework.beans.factory.annotation.Autowired;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.nce.communication.TalkToNCE;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;

/**
 * Created by shawn on 09/07/16.
 */
public abstract class AbstractNceMessage implements NceMessage {

    private TalkToNCE talkToNCE;

    @Autowired
    public void setTalkToNCE(final TalkToNCE talkToNCE) {
        this.talkToNCE = talkToNCE;
    }

    TalkToNCE getTalkToNCE() {
        return talkToNCE;
    }

    MessageResponse getMessageResponse() {
        final MessageResponse messageResponse = new MessageResponse();
        messageResponse.setStatus(MessageResponse.MessageStatus.OK);
        return messageResponse;
    }

    @Override
    public abstract MessageResponse process(final Message message) throws ConnectionException;

    static MessageResponse.MessageStatus readStatus(final Integer data) {
        MessageResponse.MessageStatus status = MessageResponse.MessageStatus.ERROR;
        if (data != null && data == 33) {
            status = MessageResponse.MessageStatus.OK;
        }
        return status;
    }

}
