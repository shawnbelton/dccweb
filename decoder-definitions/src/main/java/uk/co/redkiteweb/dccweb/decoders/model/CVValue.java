package uk.co.redkiteweb.dccweb.decoders.model;

import java.util.Collection;

public class CVValue implements Comparable<CVValue> {

    public enum Type {
        VALUE,
        FLAG,
        OPTION
    }

    private String id;
    private String cvNumber;
    private String name;
    private Type type;
    private Integer bit;
    private Integer low;
    private Integer high;
    private Integer mask;
    private Collection<CVValueOption> options;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getCvNumber() {
        return cvNumber;
    }

    public void setCvNumber(final String cvNumber) {
        this.cvNumber = cvNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(final Type type) {
        this.type = type;
    }

    public Integer getBit() {
        return bit;
    }

    public void setBit(final Integer bit) {
        this.bit = bit;
    }

    public Integer getLow() {
        return low;
    }

    public void setLow(final Integer low) {
        this.low = low;
    }

    public Integer getHigh() {
        return high;
    }

    public void setHigh(final Integer high) {
        this.high = high;
    }

    public Integer getMask() {
        return mask;
    }

    public void setMask(final Integer mask) {
        this.mask = mask;
    }

    public Collection<CVValueOption> getOptions() {
        return options;
    }

    public void setOptions(final Collection<CVValueOption> options) {
        this.options = options;
    }

    @Override
    public int compareTo(final CVValue cvValue) {
        return this.id.compareTo(cvValue.getId());
    }
}
