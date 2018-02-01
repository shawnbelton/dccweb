package uk.co.redkiteweb.dccweb.data;

import java.io.Serializable;
import java.util.List;

public class DecoderSetting implements Serializable {

    static final long serialVersionUID = 8004;

    private String name;
    private String type;
    private Integer value;
    private List<DecoderSettingOption> decoderSettingOptions;

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

    public List<DecoderSettingOption> getDecoderSettingOptions() {
        return decoderSettingOptions;
    }

    public void setDecoderSettingOptions(final List<DecoderSettingOption> decoderSettingOptions) {
        this.decoderSettingOptions = decoderSettingOptions;
    }
}
