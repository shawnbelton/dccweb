package uk.co.redkiteweb.dccweb.data.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by shawn on 24/06/16.
 */
@Entity
public class Loco implements Serializable {

    private static final long serialVersionUID = 9001;

    private Integer locoId;
    private String number;
    private String name;
    private Decoder decoder;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getLocoId() {
        return locoId;
    }

    public void setLocoId(final Integer locoId) {
        this.locoId = locoId;
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
