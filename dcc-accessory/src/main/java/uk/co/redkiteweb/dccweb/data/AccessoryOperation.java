package uk.co.redkiteweb.dccweb.data;

import java.io.Serializable;

/**
 * Created by shawn on 03/03/17.
 */
public class AccessoryOperation implements Serializable {

    static final long serialVersionUID = 8003;

    private Integer accessoryAddress;
    private Integer operationValue;

    public Integer getAccessoryAddress() {
        return accessoryAddress;
    }

    public void setAccessoryAddress(final Integer accessoryAddress) {
        this.accessoryAddress = accessoryAddress;
    }

    public Integer getOperationValue() {
        return operationValue;
    }

    public void setOperationValue(final Integer operationValue) {
        this.operationValue = operationValue;
    }
}
