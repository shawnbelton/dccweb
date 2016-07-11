package uk.co.redkiteweb.dccweb.nce.messages;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ReadCVMessage;
import uk.co.redkiteweb.dccweb.nce.communication.NceData;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;

/**
 * Created by shawn on 11/07/16.
 */
@Component("ReadCVMessage")
public class NceReadCVMessage extends AbstractNceMessage implements NceMessage {

    private static final Logger LOGGER = Logger.getLogger(NceReadCVMessage.class);

    @Override
    public MessageResponse process(final Message message) throws ConnectionException {

        final MessageResponse messageResponse = getMessageResponse();
        final NceData nceData = new NceData();
        nceData.addData(0xa9);
        nceData.addData(((ReadCVMessage)message).getCvReg() / 256);
        nceData.addData(((ReadCVMessage)message).getCvReg() % 256);
        final NceData responseData = getTalkToNCE().sendData(nceData);
        if (responseData.size() == 1) {
            if (responseData.readData() == 48) {
                messageResponse.setStatus(MessageResponse.MessageStatus.ERROR);
            }
        } else {
            messageResponse.put("CVData", responseData.readData());
            messageResponse.setStatus(readStatus(responseData.readData()));
        }
        return messageResponse;
    }

    private static MessageResponse.MessageStatus readStatus(final Integer data) {
        MessageResponse.MessageStatus status = MessageResponse.MessageStatus.ERROR;
        if (data == 33) {
            status = MessageResponse.MessageStatus.OK;
        }
        return status;
    }
}