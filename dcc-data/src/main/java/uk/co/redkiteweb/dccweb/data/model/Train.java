package uk.co.redkiteweb.dccweb.data.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by shawn on 24/06/16.
 */
@Entity
public class Train implements Serializable {

    private static final long serialVersionUID = 9001;

    private Integer trainId;
    private String number;
    private String name;
    private Decoder decoder;

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

    @OneToOne
    public Decoder getDecoder() {
        return decoder;
    }

    public void setDecoder(Decoder decoder) {
        this.decoder = decoder;
    }
}
