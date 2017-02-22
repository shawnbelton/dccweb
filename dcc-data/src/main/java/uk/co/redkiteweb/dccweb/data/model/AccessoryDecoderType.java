package uk.co.redkiteweb.dccweb.data.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

/**
 * Created by shawn on 23/01/17.
 */
@Entity
public class AccessoryDecoderType implements Serializable {
    private static final long serialVersionUID = 9007;

    private Integer decoderTypeId;
    private String decoderType;
    private String decoderTypeCode;
    private List<AccessoryDecoderTypeOperation> decoderTypeOperations;

    @Id
    public Integer getDecoderTypeId() {
        return decoderTypeId;
    }

    public void setDecoderTypeId(final Integer decoderTypeId) {
        this.decoderTypeId = decoderTypeId;
    }

    public String getDecoderType() {
        return decoderType;
    }

    public void setDecoderType(final String decoderType) {
        this.decoderType = decoderType;
    }

    public String getDecoderTypeCode() {
        return decoderTypeCode;
    }

    public void setDecoderTypeCode(String decoderTypeCode) {
        this.decoderTypeCode = decoderTypeCode;
    }

    @OneToMany
    @JoinColumn(name = "decoder_type_id")
    public List<AccessoryDecoderTypeOperation> getDecoderTypeOperations() {
        return decoderTypeOperations;
    }

    public void setDecoderTypeOperations(List<AccessoryDecoderTypeOperation> decoderTypeOperations) {
        this.decoderTypeOperations = decoderTypeOperations;
    }
}
