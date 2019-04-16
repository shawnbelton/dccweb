package uk.co.redkiteweb.dccweb.nce.messages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.messages.Message;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.dccinterface.messages.UpdateFunctionsMessage;
import uk.co.redkiteweb.dccweb.nce.communication.NceData;
import uk.co.redkiteweb.dccweb.nce.exception.ConnectionException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shawn on 21/09/16.
 */
@Component("NceUpdateFunctionsMessage")
public class NceUpdateFunctionsMessage extends AbstractNceMessage implements NceMessage {

    private static final Logger LOGGER = LogManager.getLogger(NceUpdateFunctionsMessage.class);

    private final Map<Integer, Integer> operationMap;

    public NceUpdateFunctionsMessage() {
        operationMap = new HashMap<>();
        operationMap.put(4,0x07);
        operationMap.put(8,0x08);
        operationMap.put(12,0x09);
        operationMap.put(20,0x15);
        operationMap.put(28,0x16);
    }

    @Override
    public MessageResponse process(final Message message) throws ConnectionException {
        final UpdateFunctionsMessage updateFunctionsMessage = (UpdateFunctionsMessage) message;
        final MessageResponse messageResponse = getMessageResponse();
        int value = 0;
        for (int functionNumber = 0; functionNumber <= 28; functionNumber++) {
            value |= getFunctionValue(functionNumber, updateFunctionsMessage.getFunctionState(functionNumber));
            final Integer operation = getOperation(functionNumber);
            if (operation != null) {
                final MessageResponse.MessageStatus response = updateFunctions(updateFunctionsMessage, operation, value);
                if (!MessageResponse.MessageStatus.OK.equals(response)) {
                    messageResponse.setStatus(response);
                }
                value = 0;
            }
        }
        return messageResponse;
    }

    private MessageResponse.MessageStatus updateFunctions(final UpdateFunctionsMessage updateFunctionsMessage, final int operation, final int value) throws ConnectionException {
        final NceData nceData = new NceData();
        nceData.addData(0xa2);
        nceData.addData((updateFunctionsMessage.getAddress() / 256) | getHighByteMask(updateFunctionsMessage.isAddressMode()));
        nceData.addData(updateFunctionsMessage.getAddress() % 256);
        nceData.addData(operation);
        nceData.addData(value);
        nceData.setExpectedValues(1);
        LOGGER.info("Sending operation {} value {}", operation, value);
        final NceData responseData = getTalkToNCE().sendData(nceData);
        return readStatus(responseData.readData());
    }

    private Integer getOperation(final int functionNumber) {
        Integer operation = null;
        if (operationMap.containsKey(functionNumber)) {
            operation = operationMap.get(functionNumber);
        }
        return operation;
    }

    private static int getHighByteMask(final boolean longAddress) {
        int highByteMask = 0;
        if (longAddress) {
            highByteMask = 0xc0;
        }
        return highByteMask;
    }

    private static int getFunctionValue(final int functionNumber, final boolean state) {
        int value = 0;
        if (state) {
            value = getFunctionOnValue(functionNumber).intValue();
        }
        return value;
    }

    private static Double getFunctionOnValue(int functionNumber) {
        double value;
        if (functionNumber > 12) {
            value = Math.pow(2, ((functionNumber - 13) % 8));
        } else {
            value = getLowFunctionOnValue(functionNumber);
        }
        return value;
    }

    private static double getLowFunctionOnValue(int functionNumber) {
        double value;
        if (functionNumber == 0) {
            value = Math.pow(2 , 4);
        } else {
            value = Math.pow(2 , ((functionNumber - 1) % 4));
        }
        return value;
    }

}
