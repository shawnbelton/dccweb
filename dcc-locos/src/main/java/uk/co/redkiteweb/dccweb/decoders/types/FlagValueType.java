package uk.co.redkiteweb.dccweb.decoders.types;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.DecoderSetting;

/**
 * Created by shawn on 16/09/16.
 */
@Component("flagValueType")
@Scope("prototype")
public class FlagValueType extends AbstractValueType implements ValueType {

    @Override
    public Integer getValue() {
        final String cv = getCVValue().getCvNumber();
        final long value = getCVValue(Integer.parseInt(cv));
        final long bitMask = Math.round(Math.pow(2,getCVValue().getBit()));
        final long flagValue = (value & bitMask);
        return (flagValue>0)?1:0;
    }

    @Override
    protected String getType() {
        return "flag";
    }

    @Override
    protected Integer getCVValue(final Integer cvNumber, final DecoderSetting decoderSetting) {
        final int bitMask = (int)Math.round(Math.pow(2, getCVValue().getBit()));
        return (decoderSetting.getNewValue()>0)?bitMask:0;
    }
}
