package uk.co.redkiteweb.dccweb.dccinterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.store.LogStore;
import uk.co.redkiteweb.dccweb.dccinterface.data.InterfaceInfo;
import uk.co.redkiteweb.dccweb.dccinterface.factories.MessageProcessor;
import uk.co.redkiteweb.dccweb.dccinterface.factories.MessageProcessorFactory;
import uk.co.redkiteweb.dccweb.dccinterface.messages.KeepAliveMessage;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ShutdownMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shawn on 17/06/16.
 */
@Component
public class DccInterfaceImpl implements DccInterface {

    private DccInterfaceStatus dccInterfaceStatus;
    private MessageProcessorFactory messageProcessorFactory;
    private LogStore logStore;

    @Autowired
    public void setDccInterfaceStatus(final DccInterfaceStatus dccInterfaceStatus) {
        this.dccInterfaceStatus = dccInterfaceStatus;
    }

    @Autowired
    public void setMessageProcessorFactory(final MessageProcessorFactory messageProcessorFactory) {
        this.messageProcessorFactory = messageProcessorFactory;
    }

    @Autowired
    public void setLogStore(final LogStore logStore) {
        this.logStore = logStore;
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
        try {
            final MessageResponse messageResponse = sendMessage(new KeepAliveMessage(), false);
            if (MessageResponse.MessageStatus.OK.equals(messageResponse.getStatus())) {
                dccInterfaceStatus.setConnected();
            } else {
                if ("Disconnected".equals(messageResponse.get("ERROR"))) {
                    dccInterfaceStatus.setDisconnected();
                } else {
                    dccInterfaceStatus.setOffLine();
                }
            }
        } catch (final NoClassDefFoundError | UnsatisfiedLinkError error) {
            dccInterfaceStatus.setDisconnected();
        }
    }

    @Override
    public void shutdown() {
        sendMessage(new ShutdownMessage());
    }

    @Override
    public List<InterfaceInfo> getInterfaces() {
        final List<InterfaceInfo> interfaces = new ArrayList<>();
        for(MessageProcessor messageProcessor : messageProcessorFactory.getAllInterfaces()) {
            interfaces.add(InterfaceInfo.getInstance(messageProcessor));
        }
        return interfaces;
    }

    @Override
    public MessageResponse sendMessage(final Message message) {
        return sendMessage(message, true);
    }

    private MessageResponse sendMessage(final Message message, final boolean logEntry) {
        if (logEntry) {
            logStore.log("info", String.format("Sending message: %s", message.getLogMessage()));
        }
        return messageProcessorFactory.getInstance().process(message);
    }
}
