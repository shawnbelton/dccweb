package uk.co.redkiteweb.dccweb.data;

import java.io.Serializable;
import java.util.List;

public class DecoderSetting implements Serializable {

    static final long serialVersionUID = 8004;

    private String id;
    private String name;
    private String type;
    private Integer value;
    private Integer newValue;
    private List<DecoderSettingOption> decoderSettingOptions;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(final Integer value) {
        this.value = value;
    }

    public Integer getNewValue() {
        Integer retValue = newValue;
        if (retValue == null) {
            retValue = value;
        }
        return retValue;
    }

    public void setNewValue(final Integer newValue) {
        this.newValue = newValue;
    }

    public List<DecoderSettingOption> getDecoderSettingOptions() {
        return decoderSettingOptions;
    }

    public void setDecoderSettingOptions(final List<DecoderSettingOption> decoderSettingOptions) {
        this.decoderSettingOptions = decoderSettingOptions;
    }

    public boolean isChanged() {
        return !getValue().equals(getNewValue());
    }
}
