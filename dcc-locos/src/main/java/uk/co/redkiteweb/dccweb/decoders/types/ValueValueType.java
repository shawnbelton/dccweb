package uk.co.redkiteweb.dccweb.decoders.types;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.DecoderSetting;

import java.util.Arrays;
import java.util.List;

/**
 * Created by shawn on 16/09/16.
 */
@Component("valueValueType")
@Scope("prototype")
public class ValueValueType extends AbstractValueType implements ValueType {

    @Override
    public Integer getValue() {
        int value = 0;
        for (String cv : getCVs()) {
            value *= 256;
            value += getCVValue(Integer.parseInt(cv));
        }
        final Integer mask = getReadMask();
        if (mask != null && mask != 0) {
            value &= mask;
        }
        return value;
    }

    @Override
    protected String getType() {
        return "value";
    }

    @Override
    protected Integer getCVValue(final Integer cvNumber, final DecoderSetting decoderSetting) {
        int retValue = 0;
        int value = decoderSetting.getNewValue();
        final Integer mask = getMask();
        if (mask != null && mask != 0) {
            value |= (mask ^ 0xffff);
        }
        for (String cv : Lists.reverse(getCVs())) {
            if (cvNumber.equals(Integer.parseInt(cv))) {
                retValue = value %= 256;
            }
            value /= 256;
        }
        return retValue;
    }

    private Integer getReadMask() {
        Integer mask = getMask();
        if (getCVValue().getReadMask() != null) {
            mask = getCVValue().getReadMask();
        }
        return mask;
    }

    private List<String> getCVs() {
        return Arrays.asList(getCVValue().getCvNumber().split(","));
    }

    private Integer getMask() {
        return getCVValue().getMask();
    }
}
