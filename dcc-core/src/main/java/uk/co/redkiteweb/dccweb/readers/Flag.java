package uk.co.redkiteweb.dccweb.readers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

/**
 * Created by shawn on 16/09/16.
 */
@Component
@Scope("prototype")
public class Flag implements ValueType {

    @Override
    public Integer getValue(final CVReader cvReader, final Node valueNode) {
        final String cv = valueNode.getParentNode().getAttributes().getNamedItem("number").getTextContent();
        final long value = cvReader.readCV(Integer.parseInt(cv));
        final int bit = Integer.parseInt(valueNode.getAttributes().getNamedItem("bit").getTextContent());
        final long bitMask = Math.round(Math.pow(2,bit));
        final long flagValue = (value & bitMask);
        return (flagValue>0)?1:0;
    }
}
