package uk.co.redkiteweb.dccweb.data.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by shawn on 23/01/17.
 */
@Entity
public class AccessoryDecoder implements Serializable {
    private static final long serialVersionUID = 9008;

    private Integer accessoryDecoderId;
    private AccessoryDecoderType accessoryDecoderType;
    private Integer address;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getAccessoryDecoderId() {
        return accessoryDecoderId;
    }

    public void setAccessoryDecoderId(final Integer accessoryDecoderId) {
        this.accessoryDecoderId = accessoryDecoderId;
    }

    @ManyToOne
    public AccessoryDecoderType getAccessoryDecoderType() {
        return accessoryDecoderType;
    }

    public void setAccessoryDecoderType(final AccessoryDecoderType accessoryDecoderType) {
        this.accessoryDecoderType = accessoryDecoderType;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(final Integer address) {
        this.address = address;
    }
}
