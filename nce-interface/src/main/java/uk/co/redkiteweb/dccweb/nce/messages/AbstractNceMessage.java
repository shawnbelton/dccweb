package uk.co.redkiteweb.dccweb.nce.messages;

import org.springframework.beans.factory.annotation.Autowired;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.nce.communication.TalkToNCE;

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

    static MessageResponse.MessageStatus readStatus(final Integer data) {
        MessageResponse.MessageStatus status = MessageResponse.MessageStatus.ERROR;
        if (data != null && data == 33) {
            status = MessageResponse.MessageStatus.OK;
        }
        return status;
    }

}
