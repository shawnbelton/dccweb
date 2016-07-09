package uk.co.redkiteweb.dccweb.nce.messages;

import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;

/**
 * Created by shawn on 09/07/16.
 */
public interface NceMessage {
    MessageResponse process(final Message message) throws ConnectionException;
}
