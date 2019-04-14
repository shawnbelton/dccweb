package uk.co.redkiteweb.dccweb.decoders.model;

import java.util.Collection;
import java.util.Objects;
import java.util.TreeSet;

import static uk.co.redkiteweb.dccweb.decoders.DecoderDefinition.getOrderValue;

public class CVDefinition implements Comparable<CVDefinition> {

    private String number;
    private Collection<CVValue> values;

    public CVDefinition() {
        values = new TreeSet<>();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(final String number) {
        this.number = number;
    }

    public Collection<CVValue> getValues() {
        return values;
    }

    public void setValues(final Collection<CVValue> values) {
        this.values = values;
    }

    @Override
    public int compareTo(final CVDefinition cvDefinition) {
        return getOrderValue(number).compareTo(getOrderValue(cvDefinition.getNumber()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, values);
    }

    @Override
    public boolean equals(final Object obj) {
        boolean isEqual = false;
        if (obj instanceof CVDefinition) {
            final CVDefinition that = (CVDefinition) obj;
            isEqual = Objects.equals(this.number, that.getNumber()) && areCollectionsEqual(this.values, that.getValues());
        }
        return isEqual;
    }

    private static boolean areCollectionsEqual(final Collection<CVValue> values1, final Collection<CVValue> values2) {
        return values1.size() == values2.size() && values1.containsAll(values2);
    }
}