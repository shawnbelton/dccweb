package uk.co.redkiteweb.dccweb.data;

import java.io.Serializable;

public class DecoderSettingOption implements Serializable {

    static final long serialVersionUID = 8005;

    private String option;
    private Integer value;

    public String getOption() {
        return option;
    }

    public void setOption(final String option) {
        this.option = option;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(final Integer value) {
        this.value = value;
    }
}
