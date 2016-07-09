package uk.co.redkiteweb.dccweb.nce.messages;

import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.nce.communication.NceData;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;

/**
 * Created by shawn on 09/07/16.
 */
@Component("KeepAliveMessage")
public class NceKeepAliveMessage extends AbstractNceMessage implements NceMessage {

    @Override
    public MessageResponse process(final Message message) throws ConnectionException {
        final MessageResponse messageResponse = getMessageResponse();
            final NceData requestData = new NceData();
            requestData.addData(0x80);
            final NceData responseData = getTalkToNCE().sendData(requestData);
            final Integer readData = responseData.readData();
            if (readData != null && readData == 33) {
                messageResponse.setStatus(MessageResponse.MessageStatus.OK);
                messageResponse.put("Status", "Connected");
            } else {
                messageResponse.setStatus(MessageResponse.MessageStatus.ERROR);
                messageResponse.put("Status", "Off-line");
            }
        return messageResponse;
    }
}
