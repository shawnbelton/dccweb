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
    private String number;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(final Integer trainId) {
        this.trainId = trainId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(final String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
