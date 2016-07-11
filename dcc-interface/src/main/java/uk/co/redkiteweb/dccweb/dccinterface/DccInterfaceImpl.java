package uk.co.redkiteweb.dccweb.dccinterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.factories.MessageProcessorFactory;
import uk.co.redkiteweb.dccweb.dccinterface.messages.KeepAliveMessage;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ShutdownMessage;

/**
 * Created by shawn on 17/06/16.
 */
@Component
public class DccInterfaceImpl implements DccInterface {

    private DccInterfaceStatus dccInterfaceStatus;
    private MessageProcessorFactory messageProcessorFactory;

    @Autowired
    public void setDccInterfaceStatus(final DccInterfaceStatus dccInterfaceStatus) {
        this.dccInterfaceStatus = dccInterfaceStatus;
    }

    @Autowired
    public void setMessageProcessorFactory(final MessageProcessorFactory messageProcessorFactory) {
        this.messageProcessorFactory = messageProcessorFactory;
    }

    @Override
    public DccInterfaceStatus getInterfaceStatus() {
        return dccInterfaceStatus;
    }

    @Override
    public void initialise() {
        dccInterfaceStatus.setDisconnected();
    }

    @Override
    public void checkInterface() {
        final MessageResponse messageResponse = messageProcessorFactory.getInstance().process(new KeepAliveMessage());
        if (MessageResponse.MessageStatus.OK.equals(messageResponse.getStatus())) {
            dccInterfaceStatus.setConnected();
        } else {
            if ("Disconnected".equals(messageResponse.get("ERROR"))) {
                dccInterfaceStatus.setDisconnected();
            } else {
                dccInterfaceStatus.setOffLine();
            }
        }
    }

    @Override
    public void shutdown() {
        messageProcessorFactory.getInstance().process(new ShutdownMessage());
    }

    @Override
    public MessageResponse sendMessage(final Message message) {
        return messageProcessorFactory.getInstance().process(message);
    }
}
