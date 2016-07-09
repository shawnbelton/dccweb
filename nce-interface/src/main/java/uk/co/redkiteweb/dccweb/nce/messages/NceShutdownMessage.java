package uk.co.redkiteweb.dccweb.nce.messages;

import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;

/**
 * Created by shawn on 09/07/16.
 */
@Component("ShutdownMessage")
public class NceShutdownMessage extends AbstractNceMessage implements NceMessage {

    @Override
    public MessageResponse process(final Message message) {
        getTalkToNCE().shutdown();
        return getMessageResponse();
    }
}
