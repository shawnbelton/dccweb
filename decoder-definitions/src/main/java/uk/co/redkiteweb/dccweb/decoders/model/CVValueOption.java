package uk.co.redkiteweb.dccweb.decoders.model;

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
}
