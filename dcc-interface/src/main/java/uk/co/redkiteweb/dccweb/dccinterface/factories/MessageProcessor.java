package uk.co.redkiteweb.dccweb.dccinterface.factories;

import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;

/**
 * Created by shawn on 07/07/16.
 */
public interface MessageProcessor {

    MessageResponse process(final Message message);

}
