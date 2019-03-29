package uk.co.redkiteweb.dccweb.decoders.model;

import java.util.Collection;

import static uk.co.redkiteweb.dccweb.decoders.DecoderDefinition.getOrderValue;

public class CVDefinition implements Comparable<CVDefinition> {

    private String number;
    private Collection<CVValue> values;

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

}