package uk.co.redkiteweb.dccweb.readers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

/**
 * Created by shawn on 16/09/16.
 */
@Component
@Scope("prototype")
public class Value implements ValueType {

    @Override
    public Integer getValue(final CVReader cvReader, final Node valueNode) {
        final String[] cvs = valueNode.getParentNode().getAttributes().getNamedItem("number").getTextContent().split(",");
        int value = 0;
        for(String cv : cvs) {
            value *= 256;
            value += cvReader.readCV(Integer.parseInt(cv));
        }
        final Node maskNode = valueNode.getAttributes().getNamedItem("mask");
        if (maskNode!=null) {
            value &= Integer.parseInt(maskNode.getTextContent());
        }
        return value;
    }
}
