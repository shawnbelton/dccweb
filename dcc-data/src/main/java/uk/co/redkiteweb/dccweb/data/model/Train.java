package uk.co.redkiteweb.dccweb.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by shawn on 24/06/16.
 */
@Entity
public class Train {

    private Integer trainId;
    private String trainNumber;
    private String trainName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(final Integer trainId) {
        this.trainId = trainId;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(final String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(final String trainName) {
        this.trainName = trainName;
    }
}
