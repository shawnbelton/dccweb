package uk.co.redkiteweb.dccweb.decoders.model;

import java.util.Objects;

public class CVValueOption implements Comparable<CVValueOption> {

    private Integer value;
    private String name;

    public Integer getValue() {
        return value;
    }

    public void setValue(final Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public int compareTo(final CVValueOption cvValueOption) {
        return value.compareTo(cvValueOption.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public boolean equals(final Object obj) {
        boolean isEquals = false;
        if (obj instanceof CVValueOption) {
            final CVValueOption that = (CVValueOption) obj;
            isEquals = Objects.equals(this.name, that.getName())
                    && Objects.equals(this.value, that.getValue());
        }
        return isEquals;
    }
}
