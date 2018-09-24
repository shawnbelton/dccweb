package uk.co.redkiteweb.dccweb.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

/**
 * Created by shawn on 25/07/16.
 */
@Entity
@IdClass(CVPK.class)
public class CV implements Serializable {

    private static final long serialVersionUID = 9003;

    private Integer decoderId;
    private Integer cvNumber;
    private Integer cvValue;

    @Id
    @Column(name = "decoder_id")
    public Integer getDecoderId() {
        return decoderId;
    }

    public void setDecoderId(final Integer decoderId) {
        this.decoderId = decoderId;
    }

    @Id
    public Integer getCvNumber() {
        return cvNumber;
    }

    public void setCvNumber(final Integer cvNumber) {
        this.cvNumber = cvNumber;
    }

    public Integer getCvValue() {
        return cvValue;
    }

    public void setCvValue(final Integer cvValue) {
        this.cvValue = cvValue;
    }
}
