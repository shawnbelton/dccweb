package uk.co.redkiteweb.dccweb.demo.messages;

import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;

/**
 * Created by shawn on 25/07/16.
 */
@Component("DemoExitProgramMessage")
public class DemoExitProgramMessage implements DefaultResponseDemoMessage, DemoMessage {

    @Override
    public MessageResponse process(final Message message) {
        return getMessageResponse();
    }
}
