package uk.co.redkiteweb.dccweb.nce.messages;

import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.dccinterface.messages.OperateAccessoryMessage;
import uk.co.redkiteweb.dccweb.nce.communication.NceData;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;

/**
 * Created by shawn on 06/03/17.
 */
@Component("NceOperateAccessoryMessage")
public class NceOperateAccessoryMessage extends AbstractNceMessage implements NceMessage {

    @Override
    public MessageResponse process(final Message message) throws ConnectionException {
        final OperateAccessoryMessage operateAccessoryMessage = (OperateAccessoryMessage)message;
        final MessageResponse messageResponse = getMessageResponse();
        final NceData nceData = new NceData();
        nceData.addData(0xad);
        nceData.addData(operateAccessoryMessage.getAccessoryAddress() / 256);
        nceData.addData(operateAccessoryMessage.getAccessoryAddress() % 256);
        nceData.addData(operateAccessoryMessage.getAccessoryOperation() + 3);
        nceData.addData(0);
        nceData.setExpectedValues(1);
        final NceData responseData = getTalkToNCE().sendData(nceData);
        messageResponse.setStatus(readStatus(responseData.readData()));
        return messageResponse;
    }
}
