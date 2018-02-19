package uk.co.redkiteweb.dccweb.demo.messages;

import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;

@Component("DemoWriteCVMessage")
public class DemoWriteCVMessage extends AbstractDemoMessage implements DemoMessage {

    @Override
    public MessageResponse process(final Message message) {
        return getMessageResponse();
    }
}
