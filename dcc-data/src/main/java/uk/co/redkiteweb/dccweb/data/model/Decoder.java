package uk.co.redkiteweb.dccweb.data.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by shawn on 07/07/16.
 */
@Entity
public class Decoder {

    private Integer decoderId;
    private DccManufacturer dccManufacturer;
    private Integer revision;
    private List<CV> cvs;

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

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    @OneToMany(mappedBy = "cvId")
    public List<CV> getCvs() {
        return cvs;
    }

    public void setCvs(List<CV> cvs) {
        this.cvs = cvs;
    }
}
