package uk.co.redkiteweb.dccweb.nce.messages;

import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ChangeSpeedMessage;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.nce.communication.NceData;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;

/**
 * Created by shawn on 21/09/16.
 */
@Component("NceChangeSpeedMessage")
public class NceChangeSpeedMessage extends AbstractNceMessage implements NceMessage {

    @Override
    public MessageResponse process(final Message message) throws ConnectionException {
        final ChangeSpeedMessage changeSpeedMessage = (ChangeSpeedMessage)message;
        final MessageResponse messageResponse = getMessageResponse();
        final NceData nceData = new NceData();
        nceData.addData(0xa2);
        nceData.addData((changeSpeedMessage.getAddress() / 256) | getHighByteMask(changeSpeedMessage.isAddressMode()));
        nceData.addData(changeSpeedMessage.getAddress() % 256);
        nceData.addData(getOpCode(changeSpeedMessage.getSpeedSteps(), changeSpeedMessage.getDirection()));
        nceData.addData(changeSpeedMessage.getSpeed());
        nceData.setExpectedValues(1);
        final NceData responseData = getTalkToNCE().sendData(nceData);
        messageResponse.setStatus(readStatus(responseData.readData()));
        return messageResponse;
    }

    private static int getHighByteMask(final boolean longAddress) {
        int highByteMask = 0;
        if (longAddress) {
            highByteMask = 0xc0;
        }
        return highByteMask;
    }

    private static int getOpCode(final ChangeSpeedMessage.SpeedSteps speedSteps, ChangeSpeedMessage.Direction direction) {
        int opCode = 0;
        if (ChangeSpeedMessage.Direction.UP.equals(direction)) {
            opCode = 2 + offSetSteps(speedSteps);
        } else if (ChangeSpeedMessage.Direction.DOWN.equals(direction)) {
            opCode = 1 + offSetSteps(speedSteps);
        } else if (ChangeSpeedMessage.Direction.RSTOP.equals(direction)) {
            opCode = 5;
        } else if (ChangeSpeedMessage.Direction.FSTOP.equals(direction)) {
            opCode = 6;
        }
        return opCode;
    }

    private static int offSetSteps(final ChangeSpeedMessage.SpeedSteps speedSteps) {
        int offSet = 2;
        if (ChangeSpeedMessage.SpeedSteps.STEPS_28.equals(speedSteps)) {
            offSet = 0;
        }
        return offSet;
    }
}
