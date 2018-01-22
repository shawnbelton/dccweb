package uk.co.redkiteweb.dccweb.readers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by shawn on 16/09/16.
 */
@Component("flagValueType")
@Scope("prototype")
public class Flag extends AbstractValueType implements ValueType {

    @Override
    public Integer getValue() {
        final String cv = getValueNode().getParentNode().getAttributes().getNamedItem("number").getTextContent();
        final long value = getCvReader().readCV(Integer.parseInt(cv));
        final int bit = Integer.parseInt(getValueNode().getAttributes().getNamedItem("bit").getTextContent());
        final long bitMask = Math.round(Math.pow(2,bit));
        final long flagValue = (value & bitMask);
        return (flagValue>0)?1:0;
    }

    @Override
    protected String getType() {
        return "flag";
    }
}
