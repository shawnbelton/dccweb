package uk.co.redkiteweb.dccweb.dccinterface.messages;

/**
 * Created by shawn on 06/03/17.
 */
public class OperateAccessoryMessage implements Message {

    private Integer accessoryAddress;
    private Integer accessoryOperation;

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

    @Override
    public String getLogMessage() {
        return String.format("Setting accessory %d to %d", accessoryAddress, accessoryOperation);
    }
}
