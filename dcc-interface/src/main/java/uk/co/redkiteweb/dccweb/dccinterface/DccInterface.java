package uk.co.redkiteweb.dccweb.dccinterface;

import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;

/**
 * Created by shawn on 15/06/16.
 */
public interface DccInterface {

    DccInterfaceStatus getInterfaceStatus();
    void initialise();
    void checkInterface();
    MessageResponse sendMessage(final Message message);
    void shutdown();

}
