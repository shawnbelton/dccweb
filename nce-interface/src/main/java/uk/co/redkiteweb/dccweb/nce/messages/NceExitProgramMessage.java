package uk.co.redkiteweb.dccweb.nce.messages;

import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;

/**
 * Created by shawn on 09/07/16.
 */
@Component("NceExitProgramMessage")
public class NceExitProgramMessage extends AbstractSingleResponseMessage implements NceMessage {

    @Override
    public MessageResponse process(final Message message) throws ConnectionException {
        return singleResponse(0x9f);
    }
}
