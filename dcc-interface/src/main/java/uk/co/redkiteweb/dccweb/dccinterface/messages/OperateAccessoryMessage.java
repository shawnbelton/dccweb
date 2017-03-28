package uk.co.redkiteweb.dccweb.dccinterface.messages;

/**
 * Created by shawn on 06/03/17.
 */
public class OperateAccessoryMessage implements Message {

    private Integer accessoryAddress;
    private Integer accessoryOperation;
    private String logMessage;

    public Integer getAccessoryAddress() {
        return accessoryAddress;
    }

    public void setAccessoryAddress(final Integer accessoryAddress) {
        this.accessoryAddress = accessoryAddress;
    }

    public Integer getAccessoryOperation() {
        return accessoryOperation;
    }

    public void setAccessoryOperation(final Integer accessoryOperation) {
        this.accessoryOperation = accessoryOperation;
    }

    public void setLogMessage(final String logMessage) {
        this.logMessage = logMessage;
    }

    @Override
    public String getLogMessage() {
        return logMessage;
    }
}
