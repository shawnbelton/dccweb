package uk.co.redkiteweb.dccweb.decoders.types;

import uk.co.redkiteweb.dccweb.data.DecoderSetting;
import uk.co.redkiteweb.dccweb.data.DecoderSettingOption;
import uk.co.redkiteweb.dccweb.decoders.model.CVValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractValueType implements ValueType {

    private CVValue cvValue;
    private CVHandler cvHandler;

    @Override
    public void setCVValue(final CVValue cvValue) {
        this.cvValue = cvValue;
    }

    @Override
    public void setCVReader(final CVHandler cvHandler) {
        this.cvHandler = cvHandler;
    }

    CVValue getCVValue() {
        return cvValue;
    }

    Integer getCVValue(final Integer cvNumber) {
        return cvHandler.readCV(cvNumber);
    }

    protected abstract String getType();

    @Override
    public DecoderSetting getSetting() {
        final DecoderSetting setting = new DecoderSetting();
        setting.setId(getId());
        setting.setType(getType());
        setting.setName(getName());
        setting.setValue(getValue());
        setting.setDecoderSettingOptions(getOptions());
        return setting;
    }

    @Override
    public Integer getCVValue(final Integer cvNumber, final Collection<DecoderSetting> decoderSettings) {
        Integer value = 0;
        for(DecoderSetting decoderSetting : decoderSettings) {
            if (getName().equals(decoderSetting.getName())) {
                value = getCVValue(cvNumber, decoderSetting);
            }
        }
        return value;
    }

    String getId() {
        return cvValue.getId();
    }

    private String getName() {
        return cvValue.getName();
    }

    List<DecoderSettingOption> getOptions() {
        return new ArrayList<>();
    }

    protected abstract Integer getCVValue(final Integer cvNumber, final DecoderSetting decoderSetting);

}
