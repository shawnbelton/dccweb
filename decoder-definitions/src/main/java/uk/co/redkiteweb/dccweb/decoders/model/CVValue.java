package uk.co.redkiteweb.dccweb.decoders.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.TreeSet;

import static uk.co.redkiteweb.dccweb.decoders.DecoderDefinition.getOrderValue;

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
    private Collection<Integer> bit;
    private Integer low;
    private Integer high;
    private Integer mask;
    private Integer readMask;
    private Collection<CVValueOption> options;

    public CVValue() {
        this.options = new TreeSet<>();
        this.bit = new HashSet<>();
    }

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

    private static boolean areCollectionsEqualInt(final Collection<Integer> integers1, final Collection<Integer> integers2) {
        return integers1.size() == integers2.size() && integers1.containsAll(integers2);
    }

    public Collection<Integer> getBit() {
        return bit;
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

    public Integer getReadMask() {
        return readMask;
    }

    public void setReadMask(Integer readMask) {
        this.readMask = readMask;
    }

    public Collection<CVValueOption> getOptions() {
        return options;
    }

    public void setOptions(final Collection<CVValueOption> options) {
        this.options = options;
    }

    @Override
    public int compareTo(final CVValue cvValue) {
        final int firstCompare = getOrderValue(cvNumber).compareTo(getOrderValue(cvValue.getCvNumber()));
        final int finalCompare;
        if (firstCompare == 0) {
            finalCompare = this.id.compareTo(cvValue.getId());
        } else {
            finalCompare = firstCompare;
        }
        return finalCompare;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cvNumber, name, type, bit, low, high, mask, readMask, options);
    }

    public void setBit(final Collection<Integer> bit) {
        this.bit = bit;
    }

    private static boolean areCollectionsEqual(final Collection<CVValueOption> options1, final Collection<CVValueOption> options2) {
        return options1.size() == options2.size() && options1.containsAll(options2);
    }

    @Override
    public boolean equals(final Object obj) {
        boolean isEquals = false;
        if (obj instanceof CVValue) {
            final CVValue that = (CVValue) obj;
            isEquals = Objects.equals(this.id, that.getId())
                    && Objects.equals(this.cvNumber, that.getCvNumber())
                    && Objects.equals(this.name, that.getName())
                    && Objects.equals(this.type, that.getType())
                    && areCollectionsEqualInt(this.bit, that.getBit())
                    && Objects.equals(this.low, that.getLow())
                    && Objects.equals(this.high, that.getHigh())
                    && Objects.equals(this.mask, that.getMask())
                    && Objects.equals(this.readMask, that.getReadMask())
                    && areCollectionsEqual(this.options, that.getOptions());
        }
        return isEquals;
    }
}
