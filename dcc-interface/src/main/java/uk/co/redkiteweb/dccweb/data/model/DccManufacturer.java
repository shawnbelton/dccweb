package uk.co.redkiteweb.dccweb.data.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by shawn on 30/06/16.
 */
@Entity
public class DccManufacturer implements Serializable {

    private static final long serialVersionUID = 9000;

    private Integer manufacturerId;
    private String manufacturer;
    private String country;

    @Id
    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(final Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(final String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }
}
