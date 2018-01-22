package uk.co.redkiteweb.dccweb.data;

import java.io.Serializable;

public class DecoderSetting implements Serializable {

    static final long serialVersionUID = 8004;

    private String name;
    private String type;
    private Integer value;

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
}
