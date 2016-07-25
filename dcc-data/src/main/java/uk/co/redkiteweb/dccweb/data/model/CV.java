package uk.co.redkiteweb.dccweb.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by shawn on 25/07/16.
 */
@Entity
public class CV {

    private Integer cvId;
    private Integer cvNumber;
    private Integer cvValue;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getCvId() {
        return cvId;
    }

    public void setCvId(Integer cvId) {
        this.cvId = cvId;
    }

    public Integer getCvNumber() {
        return cvNumber;
    }

    public void setCvNumber(Integer cvNumber) {
        this.cvNumber = cvNumber;
    }

    public Integer getCvValue() {
        return cvValue;
    }

    public void setCvValue(Integer cvValue) {
        this.cvValue = cvValue;
    }
}
