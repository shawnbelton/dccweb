package uk.co.redkiteweb.dccweb.demo.messages;

import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;

/**
 * Created by shawn on 21/09/16.
 */
@Component("DemoUpdateFunctionsMessage")
public class DemoUpdateFunctionsMessage extends AbstractDemoMessage implements DemoMessage {

    @Override
    public MessageResponse process(Message message) {
        return getMessageResponse();
    }
}
