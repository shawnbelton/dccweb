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
        return getBitsValue(value);
    }

    private int getBitsValue(final long cvValue) {
        int value = 0;
        int pos = 1;
        for (final Integer bit : getCVValue().getBit()) {
            value += getBitValue(cvValue, bit) * pos;
            pos *= 2;
        }
        return value;
    }

    private int getBitValue(final long cvValue, final Integer bit) {
        final long bitMask = Math.round(Math.pow(2, bit));
        final long flagValue = (cvValue & bitMask);
        return (flagValue > 0) ? 1 : 0;
    }

    @Override
    protected String getType() {
        return "flag";
    }

    @Override
    protected Integer getCVValue(final Integer cvNumber, final DecoderSetting decoderSetting) {
        int newValue = decoderSetting.getNewValue();
        int value = 0;
        for (Integer bit : getCVValue().getBit()) {
            value += getBitMask(bit, newValue % 2, decoderSetting);
            newValue /= 2;
        }
        return value;
    }

    private Integer getBitMask(final Integer bit, final int newValue, final DecoderSetting decoderSetting) {
        final int bitMask = (int) Math.round(Math.pow(2, bit));
        return (newValue > 0) ? bitMask : 0;
    }
}
