package uk.co.redkiteweb.dccweb.dccinterface.data;

import uk.co.redkiteweb.dccweb.dccinterface.factories.MessageProcessor;

import java.io.Serializable;

/**
 * Created by shawn on 20/12/16.
 */
public class InterfaceInfo implements Serializable {

    static final long serialVersionUID = 7000;

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    private void setCode(final String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    private void setName(final String name) {
        this.name = name;
    }

    public static InterfaceInfo getInstance(final MessageProcessor messageProcessor) {
        final InterfaceInfo interfaceInfo = new InterfaceInfo();
        interfaceInfo.setCode(messageProcessor.getInterfaceCode());
        interfaceInfo.setName(messageProcessor.getInterfaceName());
        return interfaceInfo;
    }
}
