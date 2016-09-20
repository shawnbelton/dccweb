package uk.co.redkiteweb.dccweb.readers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

/**
 * Created by shawn on 18/09/16.
 */
@Component
@Scope("prototype")
public class ValueTypeFactory {

    private Value value;
    private Flag flag;

    @Autowired
    public void setValue(final Value value) {
        this.value = value;
    }

    @Autowired
    public void setFlag(final Flag flag) {
        this.flag = flag;
    }

    public ValueType getInstance(final Node valueNode) {
        final String type = valueNode.getAttributes().getNamedItem("type").getTextContent();
        ValueType valueType = null;
        if ("value".equalsIgnoreCase(type)) {
            valueType = value;
        } else if ("flag".equalsIgnoreCase(type)) {
            valueType = flag;
        }
        return valueType;
    }

}
