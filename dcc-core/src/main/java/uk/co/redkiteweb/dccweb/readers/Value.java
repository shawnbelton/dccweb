package uk.co.redkiteweb.dccweb.readers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

/**
 * Created by shawn on 16/09/16.
 */
@Component("valueValueType")
@Scope("prototype")
public class Value extends AbstractValueType implements ValueType {

    @Override
    public Integer getValue() {
        final String[] cvs = getValueNode().getParentNode().getAttributes().getNamedItem("number").getTextContent().split(",");
        int value = 0;
        for(String cv : cvs) {
            value *= 256;
            value += getCVValue(Integer.parseInt(cv));
        }
        final Node maskNode = getValueNode().getAttributes().getNamedItem("mask");
        if (maskNode!=null) {
            value &= Integer.parseInt(maskNode.getTextContent());
        }
        return value;
    }

    @Override
    protected String getType() {
        return "value";
    }
}
