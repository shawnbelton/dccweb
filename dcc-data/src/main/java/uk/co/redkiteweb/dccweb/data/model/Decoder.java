package uk.co.redkiteweb.dccweb.data.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by shawn on 07/07/16.
 */
@Entity
public class Decoder implements Serializable {

    private static final long serialVersionUID = 9002;

    private Integer decoderId;
    private Integer trainId;
    private DccManufacturer dccManufacturer;
    private Integer version;
    private Integer shortAddress;
    private Integer longAddress;
    private List<CV> cvs;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getDecoderId() {
        return decoderId;
    }

    public void setDecoderId(final Integer decoderId) {
        this.decoderId = decoderId;
    }

    @Column(name = "train_id")
    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    @ManyToOne
    public DccManufacturer getDccManufacturer() {
        return dccManufacturer;
    }

    public void setDccManufacturer(final DccManufacturer dccManufacturer) {
        this.dccManufacturer = dccManufacturer;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(final Integer version) {
        this.version = version;
    }

    public Integer getShortAddress() {
        return shortAddress;
    }

    public void setShortAddress(final Integer shortAddress) {
        this.shortAddress = shortAddress;
    }

    public Integer getLongAddress() {
        return longAddress;
    }

    public void setLongAddress(final Integer longAddress) {
        this.longAddress = longAddress;
    }

    @OneToMany
    @JoinColumn(name = "decoder_id")
    public List<CV> getCvs() {
        return cvs;
    }

    public void setCvs(final List<CV> cvs) {
        this.cvs = cvs;
    }
}
