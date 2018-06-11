package uk.co.redkiteweb.dccweb.nce.messages;

import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.dccinterface.messages.WriteCVMessage;
import uk.co.redkiteweb.dccweb.nce.communication.NceData;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;

@Component("NceWriteCVMessage")
public class NceWriteCVMessage extends AbstractNceMessage implements NceMessage {

    @Override
    public MessageResponse process(final Message message) throws ConnectionException {

        final MessageResponse messageResponse = getMessageResponse();
        final NceData nceData = new NceData();
        nceData.addData(0xa9);
        nceData.addData(((WriteCVMessage)message).getCv().getCvNumber() / 256);
        nceData.addData(((WriteCVMessage)message).getCv().getCvNumber() % 256);
        nceData.addData(((WriteCVMessage)message).getCv().getCvValue());
        nceData.setExpectedValues(1);
        final NceData responseData = getTalkToNCE().sendData(nceData);
        messageResponse.setStatus(readStatus(responseData.readData()));
        return messageResponse;
    }
}
