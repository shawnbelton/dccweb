package uk.co.redkiteweb.dccweb.data.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by shawn on 08/08/16.
 */
public class CVPK implements Serializable {

    private static final long serialVersionUID = 8003;

    private Integer decoderId;
    private Integer cvNumber;

    public CVPK() {}

    public CVPK(final Integer decoderId, final Integer cvNumber) {
        this.decoderId = decoderId;
        this.cvNumber = cvNumber;
    }

    public Integer getDecoderId() {
        return decoderId;
    }

    public void setDecoderId(Integer decoderId) {
        this.decoderId = decoderId;
    }

    public Integer getCvNumber() {
        return cvNumber;
    }

    public void setCvNumber(Integer cvNumber) {
        this.cvNumber = cvNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(decoderId, cvNumber);
    }

    @Override
    public boolean equals(final Object object) {
        boolean isEqual = false;
        if (object instanceof CVPK) {
            isEqual = this.hashCode() == object.hashCode();
        }
        return isEqual;
    }
}
