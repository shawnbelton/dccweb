package uk.co.redkiteweb.dccweb.nce.messages;

import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.nce.communication.NceData;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;

/**
 * Created by shawn on 11/07/16.
 */
public abstract class AbstractSingleResponseMessage extends AbstractNceMessage {

    protected MessageResponse singleResponse(final int commandValue) throws ConnectionException {
        final MessageResponse messageResponse = getMessageResponse();
        final NceData requestData = new NceData();
        requestData.addData(commandValue);
        requestData.setExpectedValues(1);
        final NceData responseData = getTalkToNCE().sendData(requestData);
        messageResponse.setStatus(readStatus(responseData.readData()));
        return messageResponse;
    }

}
