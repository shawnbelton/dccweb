package uk.co.redkiteweb.dccweb.data.model;

import javax.persistence.*;

/**
 * Created by shawn on 07/07/16.
 */
@Entity
public class Decoder {

    private Integer decoderId;
    private DccManufacturer dccManufacturer;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getDecoderId() {
        return decoderId;
    }

    public void setDecoderId(Integer decoderId) {
        this.decoderId = decoderId;
    }

    @ManyToOne
    public DccManufacturer getDccManufacturer() {
        return dccManufacturer;
    }

    public void setDccManufacturer(DccManufacturer dccManufacturer) {
        this.dccManufacturer = dccManufacturer;
    }
}
