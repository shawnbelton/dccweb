package uk.co.redkiteweb.dccweb.demo.messages;

import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;

/**
 * Created by shawn on 25/07/16.
 */
public interface DemoMessage {

    MessageResponse process(final Message message);

}
