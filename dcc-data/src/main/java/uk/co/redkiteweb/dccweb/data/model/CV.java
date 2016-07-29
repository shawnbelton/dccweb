package uk.co.redkiteweb.dccweb.data.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by shawn on 25/07/16.
 */
@Entity
public class CV implements Serializable {

    private Integer cvId;
    private String decoderId;
    private Integer cvNumber;
    private Integer cvValue;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getCvId() {
        return cvId;
    }

    public void setCvId(final Integer cvId) {
        this.cvId = cvId;
    }

    @Column(name = "decoder_id")
    public String getDecoderId() {
        return decoderId;
    }

    public void setDecoderId(final String decoderId) {
        this.decoderId = decoderId;
    }

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
